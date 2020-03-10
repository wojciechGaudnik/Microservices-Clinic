package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.PatientNotFoundException;
import com.clinics.patient.exception.RemovalOfFinishedVisitException;
import com.clinics.patient.exception.VisitNotFoundException;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
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
    @Transactional
    public void deleteByUuid(UUID uuid) {
        Visit visit = findByUuid(uuid);

        if(visit == null){
            throw new VisitNotFoundException("Visit not found: " + uuid);
        }
        if(visit.getStatus().equals(VisitStatus.FINISHED)){
            throw new RemovalOfFinishedVisitException("Tried to removed visit with UUID: " + uuid);
        }else {
            visitRepository.deleteByuuid(uuid);
        }
    }

    @Override
    public void editVisit(UUID uuid, VisitDTO visitDTO) {
        Visit visit = visitRepository.findByuuid(uuid);

        if(visit == null){
            throw new VisitNotFoundException("Visit not found: " + uuid);
        }

        visit.setStatus(visitDTO.getStatus());
        visit.setDescription(visitDTO.getDescription());

        visitRepository.save(visit);
    }

    @Override
    @Transactional
    public Visit registerVisit(UUID patientuuid, VisitDTO visitDTO) {
        Optional<Patient> patient = patientRepository.findByuuid(patientuuid);
        Visit visit = modelMapper.map(visitDTO, Visit.class);

        if(patient.isEmpty()){
            throw new PatientNotFoundException("patient has not been found " + patientuuid);
        }

        visit.setPatient(patient.get());
        patient.get().getVisits().add(visit);
        patientRepository.save(patient.get());

        visit.setPatient(patient.get());
        patient.get().getVisits().add(visit);
        patientRepository.save(patient.get());

        try{
            patientClient.registerVisit(visitDTO);
        }catch (Exception e){
            visitRepository.deleteByuuid(visit.getUuid());
            throw e;
        }

        return visit;
        //make visit out of visitDTO


    }

    @Override
    public Visit findByUuid(UUID uuid) {
        Visit visit = visitRepository.findByuuid(uuid);

        if(visit == null){
            throw new VisitNotFoundException("Visit not found: " + uuid);
        }

        return visit;
    }
}
