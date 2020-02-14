package com.clinics.common.DTO.response;


import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class DoctorResponseDTO {
	private UUID uuid;
	private String firstName;
	private String lastName;
	private String licence;
	private String specialization;
	private String calendarsUUIDs;
	private String patientsUUIDs;
}
