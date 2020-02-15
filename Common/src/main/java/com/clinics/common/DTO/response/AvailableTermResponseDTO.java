package com.clinics.common.DTO.response;

import java.time.LocalDateTime;

public class AvailableTermResponseDTO {
    //depending on implementation of calendar/terms in doctor service
    //maybe not needed if we stick to dateTime
    //private Long termId;

    private LocalDateTime date;
    private DoctorResponseDTO doctor;
}
