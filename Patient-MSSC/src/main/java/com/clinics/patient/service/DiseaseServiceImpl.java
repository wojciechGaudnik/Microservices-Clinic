package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.DiseaseDTO;
import com.clinics.patient.entity.Disease;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.PatientNotFoundException;
import com.clinics.patient.exception.VisitNotFoundException;
import com.clinics.patient.repository.DiseaseRepository;
import com.clinics.patient.repository.VisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class DiseaseServiceImpl implements DiseaseService{
    final private DiseaseRepository diseaseRepository;
    final private VisitRepository visitRepository;
    final private ModelMapper modelMapper;
    final private RestTemplate restTemplate;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository, VisitRepository visitRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.diseaseRepository = diseaseRepository;
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public Disease addDisease(UUID visitUUID, DiseaseDTO diseaseDTO) {
        Optional<Visit> visit = visitRepository.findByVisitUUID(visitUUID);
        Disease disease = modelMapper.map(diseaseDTO, Disease.class);

        if(visit.isEmpty()){
            throw new VisitNotFoundException(visitUUID);
        }

        visit.ifPresent(theVisit-> {
            disease.getVisits().add(theVisit);
            theVisit.getDiseases().add(disease);
            diseaseRepository.save(disease);
        });
        return disease;
    }
}
