package com.clinics.common.DTO.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@ToString
public class EditUserInnerDTO {

	@Email(message = "must be a well-formed email address")
	private String email;

	@Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters")
	private String password;
}
