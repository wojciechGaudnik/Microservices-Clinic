package com.clinicsmicroservices.doctor.ui.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserDetailsRequestModel {
	@NotNull(message = "firstName cannot be null")
	private String firstName;

	@NotNull(message = "lastName cannot be null")
	private String lastName;
}
