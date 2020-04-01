package com.clinics.patient.service;


import com.clinics.common.DTO.request.outer.DiseaseDTO;
import com.clinics.patient.entity.Disease;

import java.util.UUID;

public interface DiseaseService {
    Disease addDisease(UUID visitUUID, DiseaseDTO diseaseDTO);
    void removeDisease(UUID visitUUID, UUID diseaseUUID);
}