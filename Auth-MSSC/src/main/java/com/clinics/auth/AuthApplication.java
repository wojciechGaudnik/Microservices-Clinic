package com.clinics.auth;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@SpringBootApplication
public class AuthApplication {



	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}

// pass
// role
// email
// uuid

//	@Id
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(
//			name = "UUID",
//			strategy = "org.hibernate.id.UUIDGenerator"
//	)
//	@Column(updatable = false, nullable = false)
//	private UUID id;
//
//	@Column(nullable = false)
//	@Builder.Default
//	private String role = ROLE.DOCTOR;
//
//	@Column(unique = true)
//	@NotBlank(message = "email is mandatory")
//	@Size(min = 3, max = 200, message = "email length out of range")
//	@Email(message = "email invalid")
//	private String email;