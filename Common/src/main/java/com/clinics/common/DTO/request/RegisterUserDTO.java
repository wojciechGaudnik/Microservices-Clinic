package com.clinics.common.DTO.request;

import com.clinics.common.exception.validators.RoleConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDTO {

	@Email(message = "must be a well-formed email address")
	@NotNull(message = "email cannot be null")
	private String email;

	@NotNull(message = "password cannot be null")
	@Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

	@RoleConstraint
	private String role;
}
