package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.EditVisitDTO;
import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.patient.entity.Visit;

import java.util.UUID;


public interface VisitService {
    Visit registerVisit(UUID patientuuid, VisitDTO visitDTO);
    Visit findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    void editVisit(UUID uuid, EditVisitDTO editVisitDTO);
}