package com.clinicsmicroservices.doctor.shared;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class Patient {

	private UUID uuid;

	private Long Id;

	private String firstName;
}
