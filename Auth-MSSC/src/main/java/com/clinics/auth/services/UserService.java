package com.clinics.auth.services;

import com.clinics.auth.models.UserAuth;
import com.clinics.auth.repositories.UserRepository;
import com.clinics.common.ConsoleColors;
import com.clinics.common.DTO.request.RegisterUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Slf4j(topic = "---> UserService <---")
@Service
public class UserService implements UserDetailsService, JwtProperties {

	private UserAuth userAuth;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

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
		var red = ConsoleColors.RED;

		var userAuth = modelMapper.map(registerUserDTO, UserAuth.class);
		userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
		log.warn(userAuth.getEmail());
		log.warn(userAuth.getPassword());
		log.warn(userAuth.getRole());
		userRepository.save(userAuth);
		log.warn("----------------------------");
		var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
		String token = Jwts.builder()
				.setSubject("Temporary")
				.claim("authorities", Collections.singletonList("ROLE_" + userAuth.getRole()))
				.claim("UUID", userAuth.getUuid())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
		userResponse.setToken(token);
		log.warn(userResponse.getEmail());
		log.warn(userResponse.getRole());
		log.warn(userResponse.getUuid().toString());
		log.warn(userResponse.getToken());
		return userResponse;

	}

	public String getUserByUUID(String UUID){
		return "User service test" + UUID;
	}
}
