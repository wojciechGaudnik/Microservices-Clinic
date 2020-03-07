package com.clinics.common.DTO.request.outer;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@ToString
public class RegisterDoctorDTO {

	@NotNull(message = "uuid cannot be null")
	private UUID doctoruuid;

	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;


	@Size(max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

	@NotBlank(message = "licence is mandatory")
	@Size(max = 100, message = "licence length out of range ")
	private String licence;
}


