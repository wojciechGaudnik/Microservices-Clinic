package com.clinics.common.DTO.request.outer.doctor;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

//test2 discord

@Data
@ToString
public class AddEditCalendarDTO {

	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;
}
