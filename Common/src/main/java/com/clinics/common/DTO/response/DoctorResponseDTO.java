package com.clinics.common.DTO.response;


import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class DoctorResponseDTO {
	private UUID doctor_uuid;
	private String firstName;
	private String lastName;
	private String photoUrl;
	private String licence;

	private Collection<Object> calendars;
	private Collection<Object> specializations;

//	private Collection<UUID> patientsUUIDs;
	private Collection<UUID> medicalUnits;

}
