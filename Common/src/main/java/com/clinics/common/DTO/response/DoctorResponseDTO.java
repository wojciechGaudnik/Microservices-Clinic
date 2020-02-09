package com.clinics.common.DTO.response;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DoctorResponseDTO {
	private UUID uuid;
	private String firstName;
	private String lastName;
	private String email;
	private String licence;
	private String specialization;
	private String calendarsUUIDs;
	private String patientsUUIDs;
}
