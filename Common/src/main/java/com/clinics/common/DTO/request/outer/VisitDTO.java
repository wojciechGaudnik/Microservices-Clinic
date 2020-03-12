package com.clinics.common.DTO.request.outer;

import com.clinics.common.patient.VisitStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
public class VisitDTO {

    @NotNull(message = "Uuid cannot be null")
    private UUID doctorUUID;

    @NotNull(message = "Uuid cannot be null")
    private UUID appointmentUUID;

    @NotNull(message = "Uuid cannot be null")
    private UUID calendarUUID;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @Size(max = 1000, message = "Description length out of range")
    private String description;

    private VisitStatus status = VisitStatus.NEW;
}
