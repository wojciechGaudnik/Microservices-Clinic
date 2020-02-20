package com.clinics.patient.service;

import com.clinics.common.DTO.request.VisitDTO;

import java.util.UUID;

public interface VisitService {
    VisitDTO registerVisit(VisitDTO visitDTO, UUID patientUUID);
}
