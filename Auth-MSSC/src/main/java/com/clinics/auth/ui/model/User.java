package com.clinics.auth.ui.model;

import com.clinics.common.security.Role;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode
@Entity(name = "auth_user")
public class User implements Role, Serializable, UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(updatable = false, nullable = false)
	private UUID uuid; //todo uuid should be null if we want edit email or pass

	@Column(unique = true)
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

	@Column(name = "created", updatable = false, nullable = false)
	@CreationTimestamp
	private LocalDateTime creationDateStamp;

	@Column(name = "updated", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updateDateStamp;

	@Builder.Default
	private boolean isEnable = false;

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
