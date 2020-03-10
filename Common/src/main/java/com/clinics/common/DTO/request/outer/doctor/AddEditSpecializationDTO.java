package com.clinics.common.DTO.request.outer.doctor;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@ToString
public class AddEditSpecializationDTO {

	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

}
