package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class PatientResponseDTO { //todo patientRegisterResponse <---> the same as this ?
	private UUID patientUUID;
	private String firstName;
	private String lastName;
	private String photoUrl;
	private String pesel;

	private Collection<UUID> visits;
}
