package com.clinics.common.DTO.request.outer;

import com.clinics.common.patient.DiseaseName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiseaseDTO {
    private DiseaseName diseaseName;
}
