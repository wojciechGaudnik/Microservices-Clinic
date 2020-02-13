package com.clinics.auth.models;

import com.clinics.common.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@Entity(name = "auth_user")
public class AuthUser implements Role, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Builder.Default
	@Column(updatable = false, nullable = false)
	private UUID uuid = UUID.randomUUID();

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
	private String role = Role.PATIENT;

	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@Column(unique = true)
	@Size(min = 3, max = 100, message = "length out of range ")
	private String photoUrl;

	@ElementCollection
	private List<UUID> medicalUnits = new ArrayList<>();
}
