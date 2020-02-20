package com.clinics.patient.service;

import com.clinics.common.DTO.request.VisitDTO;
import com.clinics.common.DTO.response.VisitRegisterResponseDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class VisitServiceImpl implements VisitService {
    private PatientClient patientClient;
    private VisitRepository visitRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public VisitServiceImpl(PatientClient patientClient, VisitRepository visitRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientClient = patientClient;
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VisitDTO registerVisit(VisitDTO visitDTO, UUID patientUUID) {
        //uderzanie do doctora - zwrotka true or false
        VisitRegisterResponseDTO resp = patientClient.registerVisit(visitDTO);
        Patient patient = patientRepository.findByUuid(patientUUID);
        //make visit out of visitDTO
        Visit visit = modelMapper.map(visitDTO, Visit.class);
        visitDTO.setUuid(visit.getUuid());

        //Hibernate bidirectional relation
        visit.setPatient(patient);
        patient.getVisits().add(visit);

        patientRepository.save(patient);

       //TODO exception handling
        return visitDTO;
    }
}
