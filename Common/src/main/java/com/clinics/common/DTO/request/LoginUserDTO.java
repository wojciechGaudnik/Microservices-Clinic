package com.clinics.common.DTO.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginUserDTO {

	@Email
	@NotNull(message = "email cannot be null")
	private String email;

	@Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters")
	private String password;
}
