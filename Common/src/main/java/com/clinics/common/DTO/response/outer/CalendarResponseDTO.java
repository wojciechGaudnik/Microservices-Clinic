package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class CalendarResponseDTO {

	private UUID calendarUUID;
	private String name;
	private UUID medicalUnitUUID;
//	private Collection<Appointment>
}
