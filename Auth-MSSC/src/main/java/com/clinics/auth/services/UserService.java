package com.clinics.auth.services;

import com.clinics.auth.models.UserAuth;
import com.clinics.auth.repositories.UserRepository;
import com.clinics.common.DTO.request.RegisterUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
//	@Qualifier("userRepository")
	private UserRepository userRepository;
	private UserAuth userAuth;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		this.userAuth = this.userRepository.findByEmail(email);
		return this.userAuth;
	}

//	public UserAuth saveUser(RegisterUserDTO registerUserDTO) {
//
//	}

	public String getUserByUUID(String UUID){
		return "User service test" + UUID;
	}
}
