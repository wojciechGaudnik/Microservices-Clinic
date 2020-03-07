package com.clinics.common.DTO.response.inner;

import com.clinics.common.DTO.response.outer.DoctorResponseDTO;

import java.time.LocalDateTime;

public class AvailableTermResponseDTO {
    private LocalDateTime date;
    private DoctorResponseDTO doctor;
}
