package com.clinics.patient.service;

import com.clinics.patient.entity.Visit;

import java.util.UUID;

public interface VisitService {
    Visit registerVisit(Visit visit, UUID patientUUID);
}
