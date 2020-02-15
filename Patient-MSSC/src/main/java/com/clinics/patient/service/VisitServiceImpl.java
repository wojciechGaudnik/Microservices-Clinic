package com.clinics.patient.service;

import com.clinics.common.DTO.response.VisitRegisterResponseDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    PatientClient patientClient;

    @Autowired
    VisitRepository visitRepository;

    @Override
    public Visit registerVisit(Visit visit, UUID patientUUID) {
        try {
            VisitRegisterResponseDTO resp = patientClient.registerVisit(visit);
            //maybe fill some termID to the visit
            //wyciagnac pacjenta z bazy i dodac mu visit i zapisac pacjenta
            visitRepository.save(visit);
        }catch (Exception e){
            //what kind of exception to catch ????
            //log the error
            //zwrocic na front
        }
        return visit;
    }
}
