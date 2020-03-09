package com.clinics.auth.ui.service;

import com.clinics.auth.configuration.InactiveUserRemover;
import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import com.clinics.auth.security.JwtMaker;
import com.clinics.common.DTO.request.inner.EditUserDTO;
import com.clinics.common.DTO.request.outer.RegisterUserDTO;
import com.clinics.common.DTO.response.outer.UserResponseDTO;
import com.clinics.common.DTO.response.outer.UserUUIDAndROLE;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Transactional
@Service
public class UserService implements UserDetailsService, JwtMaker, JwtProperties {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private TaskScheduler taskScheduler;

	@Value("${auth.unfinished.registration.removal.timeout.millis}")
	private Long autoRemovalTimeout;


	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, TaskScheduler taskScheduler) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.taskScheduler = taskScheduler;
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
		taskScheduler.schedule(new InactiveUserRemover(userRepository, userAuthId.get()), new Date( System.currentTimeMillis() + autoRemovalTimeout));
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


	public UserResponseDTO editUser(EditUserDTO editUserDTO, UUID userUUID) {
		var optionalUserToEdit = userRepository.findByUuid(userUUID);
		if (optionalUserToEdit.isEmpty()) {
			throw new NoSuchElementException("No such user to edit ");
		}
		var userToEdit = optionalUserToEdit.get();
		if(editUserDTO.getPassword() != null) userToEdit.setPassword(passwordEncoder.encode(editUserDTO.getPassword()));
		if(editUserDTO.getEmail() != null) userToEdit.setEmail(editUserDTO.getEmail());
		userRepository.save(userToEdit);
		return modelMapper.map(userToEdit, UserResponseDTO.class);
	}

	public Long deleteUser(UUID uuid) {
		return userRepository.deleteByUuid(uuid);
	}

	public UserUUIDAndROLE getUUIDAndRole(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
		Claims claims = Jwts.parser()
				.setSigningKey(TOKEN_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return UserUUIDAndROLE
				.builder()
				.uuid(UUID.fromString(String.valueOf(claims.get(TOKEN_CLAIM_UUID))))
				.role(claims.get(JwtProperties.TOKEN_CLAIM_AUTHORITIES)
						.toString()
						.replace("[", "")
						.replace("]", "")
						.replace(JwtProperties.TOKEN_PREFIX_ROLE, ""))
				.build();
	}
}
