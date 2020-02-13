package com.clinics.auth.security;

import com.clinics.auth.models.AuthUser;
import com.clinics.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private AuthUser authUser;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		this.authUser = this.userRepository.findByEmail(s);
		return new AuthUserDetails(this.authUser);
	}

}
