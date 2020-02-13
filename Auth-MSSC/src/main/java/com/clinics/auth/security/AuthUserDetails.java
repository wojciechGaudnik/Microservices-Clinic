package com.clinics.auth.security;

import com.clinics.auth.models.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {

	private AuthUser authUser;

	public AuthUserDetails(AuthUser authUser) {
		this.authUser = authUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + authUser.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.authUser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.authUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public AuthUser getAuthUser(){
		return this.authUser;
	}
}
