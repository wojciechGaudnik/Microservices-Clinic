package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class AppointmentResponseDTO {

	private UUID appointmentUUID;

	private LocalDateTime dateTime;
	private Duration duration;

	private UUID patientUUID;
	private String patientFirstName;
	private String patientSecondName;
}
