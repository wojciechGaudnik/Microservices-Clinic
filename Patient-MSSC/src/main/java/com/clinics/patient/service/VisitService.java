package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.EditVisitDTO;
import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.patient.entity.Visit;

import java.util.UUID;


public interface VisitService {
    Visit registerVisit(UUID patientUUID, VisitDTO visitDTO);
    Visit findByUuid(UUID visitUUID);
    void deleteByUuid(UUID visitUUID);
    void editVisit(UUID visitUUID, EditVisitDTO editVisitDTO);
}