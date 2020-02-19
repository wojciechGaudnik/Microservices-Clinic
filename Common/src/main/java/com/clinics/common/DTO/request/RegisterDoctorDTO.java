package com.clinics.common.DTO.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class RegisterDoctorDTO {

	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;


	@Size(max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

	@NotBlank(message = "licence is mandatory")
	private String licence;

	private UUID uuid;
}
