package com.clinics.auth.ui.service;

import com.clinics.auth.configuration.AsyncUserRepositoryAccess;
import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import com.clinics.auth.security.JwtMaker;
import com.clinics.common.DTO.request.EditUserInnerDTO;
import com.clinics.common.DTO.request.RegisterUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService implements UserDetailsService, JwtMaker, JwtProperties {

	final private UserRepository userRepository;
	final private ModelMapper modelMapper;
	final private PasswordEncoder passwordEncoder;
	TaskExecutor taskExecutor;


	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, TaskExecutor taskExecutor) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.taskExecutor = taskExecutor;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(email).orElseThrow();
	}


	public UserResponseDTO saveUser(RegisterUserDTO registerUserDTO) {
		var userAuth = modelMapper.map(registerUserDTO, User.class);
		userAuth.setUuid(UUID.randomUUID());
		userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
		userAuth.setEnable(false);
		userRepository.save(userAuth);
		var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
		String token = makeJwtToken(userAuth);
		userResponse.setToken(TOKEN_PREFIX + token);
		AtomicLong userAuthId = new AtomicLong(userAuth.getId());
		taskExecutor.execute(new AsyncUserRepositoryAccess(userRepository, userAuthId.get()));
		return userResponse;
	}

	public UserResponseDTO setUserEnable(UUID userUUID) {
		Optional<User> user = userRepository.findByUuid(userUUID);
		if (user.isEmpty() || user.get().isEnable()) {
			throw new NoSuchElementException("User not found");
		}
		var updatedUser = user.get();
		updatedUser.setEnable(true);
        userRepository.save(updatedUser);
        return modelMapper.map(updatedUser, UserResponseDTO.class);
	}


	public UserResponseDTO editUser(EditUserInnerDTO editUserInnerDTO, UUID userUUID) {
		var optionalUserToEdit = userRepository.findByUuid(userUUID);
		if (optionalUserToEdit.isEmpty()) {
			throw new NoSuchElementException("No such user to edit ");
		}
		var userToEdit = optionalUserToEdit.get();
		if(editUserInnerDTO.getPassword() != null) userToEdit.setPassword(passwordEncoder.encode(editUserInnerDTO.getPassword()));
		if(editUserInnerDTO.getEmail() != null) userToEdit.setEmail(editUserInnerDTO.getEmail());
		userRepository.save(userToEdit);
		return modelMapper.map(userToEdit, UserResponseDTO.class);
	}

	public UUID getUUID(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
		Claims claims = Jwts.parser()
				.setSigningKey(TOKEN_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return UUID.fromString(String.valueOf(claims.get(TOKEN_CLAIM_UUID)));
	}
}
