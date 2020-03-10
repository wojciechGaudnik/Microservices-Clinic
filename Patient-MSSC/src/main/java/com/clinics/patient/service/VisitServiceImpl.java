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
        Optional<Visit> visit = visitRepository.findByuuid(uuid);

        if(visit.isEmpty()){
            throw new VisitNotFoundException(uuid);
        }
        if(visit.get().getStatus().equals(VisitStatus.FINISHED)){
            throw new RemovalOfFinishedVisitException(uuid);
        }else {
            visitRepository.deleteByuuid(uuid);
        }
    }

    @Override
    public void editVisit(UUID uuid, VisitDTO visitDTO) {
        Optional<Visit> visit = visitRepository.findByuuid(uuid);

        if(visit.isEmpty()){
            throw new VisitNotFoundException(uuid);
        }

        visit.ifPresent(theVisit -> {
            theVisit.setStatus(visitDTO.getStatus());
            theVisit.setDescription(visitDTO.getDescription());

            visitRepository.save(theVisit);
        });
    }

    @Override
    @Transactional
    public Visit registerVisit(UUID patientuuid, VisitDTO visitDTO) {
        Optional<Patient> patient = patientRepository.findByuuid(patientuuid);
        Visit visit = modelMapper.map(visitDTO, Visit.class);

        if(patient.isEmpty()){
            throw new PatientNotFoundException(patientuuid);
        }

        patient.ifPresent(thePatient-> {
            visit.setPatient(thePatient);
            thePatient.getVisits().add(visit);
            patientRepository.save(thePatient);

            visit.setPatient(thePatient);
            thePatient.getVisits().add(visit);
            patientRepository.save(thePatient);

            try{
                patientClient.registerVisit(visitDTO);
            }catch (Exception e){
                visitRepository.deleteByuuid(visit.getUuid());
                throw e;
            }
        });
        return visit;
    }

    @Override
    public Visit findByUuid(UUID uuid) {
        Optional<Visit> visit = visitRepository.findByuuid(uuid);

        if(visit.isPresent()) {
            return visit.get();
        }else{
            throw new VisitNotFoundException(uuid);
        }
    }
}
