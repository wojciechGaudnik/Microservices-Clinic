package com.clinics.common.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
public class VisitDTO {
    //TODO na localDateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private UUID doctorUUID;
    private UUID patientUUID;
}
