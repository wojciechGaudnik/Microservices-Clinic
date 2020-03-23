package com.clinics.common.DTO.request.outer.doctor;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@ToString
public class EditDoctorDTO {

	@Email(message = "must be a well-formed email address")
	private String email;

	@Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@Size(max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

	@Size(max = 100, message = "licence length out of range ")
	private String licence;
}
