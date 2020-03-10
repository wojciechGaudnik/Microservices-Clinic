package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class MedicalUnitResponseDTO {
	private UUID medicalUnitUUID;
	private String name;
	private String address;

	private Collection<UUID> doctors;
}
