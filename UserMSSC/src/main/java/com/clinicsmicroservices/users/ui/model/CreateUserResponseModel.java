package com.clinicsmicroservices.users.ui.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CreateUserResponseModel {
	private String firstName;
	private String lastName;
	private String email;
	private String userId;
}
