package com.clinicsmicroservices.doctor.ui.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequestModel {
	private String firstName;
	private String lastName;
	private String password;
	private String email;

}
