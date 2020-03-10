package com.clinics.common.DTO.request.outer;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@ToString
public class EditPatientDTO {

	@PESEL //TODO is it ok here ?
	@Size(min = 11, max = 11, message = "pesel must have exactly 11 characters")
	private String pesel;

	//TODO add more

}
