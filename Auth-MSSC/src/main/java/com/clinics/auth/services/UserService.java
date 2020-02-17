package com.clinics.auth.services;

import com.clinics.auth.models.AuthUser;
import com.clinics.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	private AuthUser authUser;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		this.authUser = this.userRepository.findByEmail(email);
		return this.authUser;
	}

	public String getUserByUUID(String UUID){
		return "User service test" + UUID;
	}
}
