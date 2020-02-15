package com.clinics.common.DTO.request;

import com.clinics.common.DTO.response.AvailableTermResponseDTO;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RegisterVisitDTO {

	/**
	 * UUID of a patient registering for a visit
	 */
	private UUID patientUUID;
	private UUID doctorUUID;

	//depending on implementation of calendar/terms in doctor service
	//maybe not needed if we stick to dateTime
	//private Long termId;

	private LocalDateTime date;
}
