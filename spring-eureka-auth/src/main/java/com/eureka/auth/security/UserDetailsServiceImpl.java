package com.eureka.auth.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.eureka.auth.model.MedicalUser;
import com.eureka.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service   		// It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MedicalUser> user = userRepository.findByUsername(username);

		if(!user.isPresent()){
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}else {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
			.commaSeparatedStringToAuthorityList("ROLE_" + user.get().getRole());

			//The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
			// And used by auth manager to verify and check user authentication.
			User usr = new User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
			System.out.println(new User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities));
			return new User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
		}
	}
}