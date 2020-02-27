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
    public Visit findByUuid(UUID uuid) {
        return visitRepository.findByUuid(uuid);
    }

    @Override
    public VisitDTO registerVisit(VisitDTO visitDTO) {
        //TODO opakowac w try

        VisitRegisterResponseDTO resp = patientClient.registerVisit(visitDTO);
        Patient patient = patientRepository.findByUuid(visitDTO.getPatientUUID());
        System.out.println(patient);

        //make visit out of visitDTO
        Visit visit = modelMapper.map(visitDTO, Visit.class);
        visit.setPatient(patient);
        System.out.println(visit);
        patient.getVisits().add(visit);
        patientRepository.save(patient);
        return visitDTO;
    }

    //TODO dane pacjenta
    @Override
    public Visit findAllDetails(UUID uuid) {
        Visit visit = findByUuid(uuid);
        return visit;
    }
}
