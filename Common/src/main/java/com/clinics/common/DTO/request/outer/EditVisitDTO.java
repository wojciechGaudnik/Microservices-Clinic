package com.clinics.common.DTO.request.outer;

import com.clinics.common.patient.VisitStatus;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@ToString
public class EditVisitDTO {

    @Size(max = 1000, message = "Description length out of range")
    private String description;

    private VisitStatus status;
}
