package com.clinics.common.DTO.request.outer;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ToString
public class EditPatientDTO {

	@PESEL
	@Size(min = 11, max = 11, message = "pesel must have exactly 11 characters")
	private String pesel;

	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@Size(min = 3, max = 100, message = "length out of range ")
	private String photoUrl;
}
