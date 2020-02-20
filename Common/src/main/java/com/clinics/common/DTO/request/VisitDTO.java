package com.clinics.common.DTO.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VisitDTO {
    private UUID uuid;
    private LocalDateTime date;
    private UUID doctorUUID;
    private UUID patientUUID;
}
