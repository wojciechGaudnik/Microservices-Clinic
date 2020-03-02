package com.clinics.common.DTO.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@ToString
public class EditDoctorDTO {

	@Email(message = "must be a well-formed email address")
//	@NotNull(message = "email cannot be null")
	private String email;

//	@NotNull(message = "password cannot be null")
	@Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than 16 characters")
	private String password;

//	@NotNull(message = "uuid cannot be null")
//	private UUID doctoruuid;

//	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

//	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@Size(max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

//	@NotBlank(message = "licence is mandatory")
	@Size(max = 100, message = "licence length out of range ")
	private String licence;
}
