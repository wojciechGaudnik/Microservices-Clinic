package com.clinics.common.DTO.request.outer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
public class VisitDTO {
    //TODO na localDateTime

    //for creation of path to call doctor service:
    private UUID doctorUUID;
    private String appointmentId;
    private String calendarId;

    //patient only :
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private String visitNotes;
}
