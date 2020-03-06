package com.clinics.patient.service;

import com.clinics.common.DTO.request.VisitDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;

import java.util.UUID;


public interface VisitService {
    VisitDTO registerVisit(VisitDTO visitDTO);
    Visit findAllDetails(UUID uuid);
    Visit findByUuid(UUID uuid);
}
