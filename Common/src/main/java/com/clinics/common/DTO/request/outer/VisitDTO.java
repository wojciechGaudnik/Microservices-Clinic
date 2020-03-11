package com.clinics.common.DTO.request.outer;

import com.clinics.common.patient.VisitStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
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

    //TODO na localDateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    @Size(max = 1000, message = "Description length out of range")
    private String description;

    private VisitStatus status = VisitStatus.NEW;
}
