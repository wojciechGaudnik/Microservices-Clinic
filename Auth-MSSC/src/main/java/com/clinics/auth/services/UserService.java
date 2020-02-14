package com.clinics.auth.services;

import com.clinics.auth.models.UserAuth;
import com.clinics.auth.repositories.UserRepository;
import com.clinics.auth.security.JwtMaker;
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


@Slf4j(topic = "---> UserService <---")
@Service
public class UserService implements UserDetailsService, JwtMaker {

	private UserAuth userAuth;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		this.userAuth = this.userRepository.findByEmail(email);
		return this.userAuth;
	}

	public UserResponseDTO saveUser(RegisterUserDTO registerUserDTO) {
		var userAuth = modelMapper.map(registerUserDTO, UserAuth.class);
		userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
		userRepository.save(userAuth);
		var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
		String token = makeJwtToken(userAuth);
		userResponse.setToken(TOKEN_PREFIX + token);
		return userResponse;

	}

	public String getUserByUUID(String UUID){
		return "User service test" + UUID;
	}
}
