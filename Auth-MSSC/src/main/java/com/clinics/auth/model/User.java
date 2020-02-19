package com.clinics.auth.model;

import com.clinics.auth.exception.validator.UniqueEmailConstraint;
import com.clinics.common.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@Entity(name = "auth_user")
public class User implements Role, Serializable, UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Builder.Default
	@Column(updatable = false, nullable = false)
	private UUID uuid = UUID.randomUUID();

	@Column(unique = true)
	@UniqueEmailConstraint
	@NotBlank(message = "email is mandatory")
	@Size(min = 3, max = 200, message = "email length out of range")
	@Email(message = "email invalid")
	private String email;


	@NotBlank(message = "password is mandatory")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 8, max = 100, message = "password length out of range")
	private String password;

	@Column(nullable = false)
	private String role;

	@Builder.Default
	private boolean isEnable = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.role);
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
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
		return this.isEnable;
	}
}
