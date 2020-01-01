package com.clinicsmicroservices.users.ui.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateUserRequestModel {

	@NotNull(message = "First name cannot be null")
	@Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters")
	private String firstName;

	@NotNull(message = "Last name cannot be null")
	@Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters")
	private String lastName;

	@NotNull(message = "Password name cannot be null")
	@Size(min = 8, max = 16, message = "Password should be between 8 and 16 characters")
	private String password;

	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
}
