package com.clinics.common.DTO.request.outer.doctor;

import lombok.*;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class AppointmentDTO {

	private LocalDateTime localDateTime;
	@DurationMax(minutes = 60)
	@DurationMin(minutes = 10)
	private Duration duration;

	private UUID patientUUID;
	private String patientFirstName;
	private String patientSecondName;

}
