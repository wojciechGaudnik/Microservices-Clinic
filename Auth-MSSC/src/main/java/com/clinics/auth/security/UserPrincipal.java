package com.clinics.auth.security;

import com.clinics.auth.models.ClinicUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

	private ClinicUser clinicUser;

	public UserPrincipal(ClinicUser clinicUser) {
		this.clinicUser = clinicUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + clinicUser.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.clinicUser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.clinicUser.getEmail();
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

	public ClinicUser getClinicUser(){
		return this.clinicUser;
	}
}
