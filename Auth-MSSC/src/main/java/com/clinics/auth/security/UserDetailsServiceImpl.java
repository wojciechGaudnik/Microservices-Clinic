package com.clinics.auth.security;

import com.clinics.common.security.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final List<AppUser> users = Arrays.asList(
				new AppUser(1, "jan", encoder.encode("12345"), Role.DOCTOR),
				new AppUser(2, "creepy", encoder.encode("12345"), Role.SYSTEM_ADMIN)
		);
		for (AppUser appUser : users) {
			if (appUser.getUsername().equals(userName)) {
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
						.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
			}
		}
		throw new UsernameNotFoundException("UserName: " + userName + " not found");
	}

	// A (temporary) class represent the user saved in the database.
	@Data
	private static class AppUser {
		private Integer id;
		private String username, password;
		private String role;

		public AppUser(Integer id, String username, String password, String role) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}
	}
}
