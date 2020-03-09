package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return visitRepository.findByuuid(uuid);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        visitRepository.deleteByuuid(uuid);
    }

    @Override
    @Transactional
    public VisitDTO registerVisit(UUID patientuuid, VisitDTO visitDTO) {
        //TODO opakowac w try

        //1. Proba zapisu wizyty ppo stronie pacjenta w stanie (pending)
//        VisitRegisterResponseDTO resp = patientClient.registerVisit(visitDTO);
        Patient patient = patientRepository.findByuuid(patientuuid);

        //make visit out of visitDTO
        Visit visit = modelMapper.map(visitDTO, Visit.class);
        visit.setPatient(patient);
        patient.getVisits().add(visit);
        patientRepository.save(patient);

        //2. uderzamy do doctora ..... z zapisem tej wizyty
        try{
            //uderzenie do doctora
            patientClient.registerVisit(visitDTO);
            //throw new RuntimeException("blad");
        }catch (Exception e){
            visitRepository.deleteByuuid(visit.getUuid());
            throw e;
        }

        //3. jeeli doctor odpowie OK, to zmieniamy statuzs wizyty po stronie pacjenta na ACTIVE

        //??? kartoteki, historia choroby....
        //??? endpointy wystawione dla doctora
        //tutaj tylko zwrotka 200 albo 204 - no content jeżeli OK
        return visitDTO;
    }

    //TODO dane pacjenta
    @Override
    public Visit findAllDetails(UUID uuid) {
        Visit visit = findByUuid(uuid);
        return visit;
    }
}
