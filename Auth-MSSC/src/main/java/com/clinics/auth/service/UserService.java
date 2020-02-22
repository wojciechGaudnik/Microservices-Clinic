package com.clinics.auth.service;

import com.clinics.auth.model.User;
import com.clinics.auth.repositorie.UserRepository;
import com.clinics.auth.security.JwtMaker;
import com.clinics.common.ConsoleColors;
import com.clinics.common.DTO.request.RegisterUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService implements UserDetailsService, JwtMaker {

	final private UserRepository userRepository;
	final private ModelMapper modelMapper;
	final private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(email).orElseThrow();
	}

	public UserResponseDTO saveUser(RegisterUserDTO registerUserDTO) {
		var userAuth = modelMapper.map(registerUserDTO, User.class);
		userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
		userAuth.setEnable(false);
		userRepository.save(userAuth);
		var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
		String token = makeJwtToken(userAuth);
		userResponse.setToken(TOKEN_PREFIX + token);
		return userResponse;
	}

	public UserResponseDTO setUserEnable(UUID userUUID) {
		log.warn(userUUID + "<--- uid from service ");
		log.warn((userUUID.getClass()) + " <--- uid from service class");
		Optional<User> user = userRepository.findByUuid(userUUID);
		if (user.isEmpty()) {
			log.warn("Uesr is empty <---------------");
			throw new NoSuchElementException("User not found");
		}
		log.warn((user.get().getUuid() + " <--- user uuid from repository "));
		var updatedUser = user.get();
		updatedUser.setEnable(true);
		log.warn(ConsoleColors.RED_BRIGHT + (updatedUser) + ConsoleColors.RESET);
        userRepository.save(updatedUser);
        return modelMapper.map(updatedUser, UserResponseDTO.class);

	}

}
