package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.RemovalOfFinishedVisitException;
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
    @Transactional
    public void deleteByUuid(UUID uuid) {
        if(findByUuid(uuid).getStatus().equals(VisitStatus.FINISHED)){
            throw new RemovalOfFinishedVisitException("Tried to removed visit with UUID: " + uuid);
        }else {
            visitRepository.deleteByuuid(uuid);
        }
    }

    @Override
    public void editVisit(UUID uuid, VisitDTO visitDTO) {
        Visit visit = findByUuid(uuid);

        visit.setStatus(visitDTO.getStatus());
        visit.setDescription(visitDTO.getDescription());

        visitRepository.save(visit);
    }

    @Override
    @Transactional
    public VisitDTO registerVisit(UUID patientuuid, VisitDTO visitDTO) {
        Patient patient = patientRepository.findByuuid(patientuuid);

        //make visit out of visitDTO
        Visit visit = modelMapper.map(visitDTO, Visit.class);
        visit.setPatient(patient);
        patient.getVisits().add(visit);
        patientRepository.save(patient);

        try{
            patientClient.registerVisit(visitDTO);
        }catch (Exception e){
            visitRepository.deleteByuuid(visit.getUuid());
            throw e;
        }

        //TODO 3. jezeli doctor odpowie OK, to zmieniamy statuzs wizyty po stronie pacjenta na ACTIVE, nie usuwamy od razu, do przemyslenia

        //??? kartoteki, historia choroby, recepty, badania, wyniki

        return visitDTO;
    }

    //TODO dane pacjenta
    @Override
    public Visit findAllDetails(UUID uuid) {
        Visit visit = findByUuid(uuid);
        return visit;
    }

    @Override
    public Visit findByUuid(UUID uuid) {
        return visitRepository.findByuuid(uuid);
    }

//    @Override
//    public Visit findByUuid(UUID uuid) {
//        return visitRepository.findByUUID(uuid);
//    }
}
