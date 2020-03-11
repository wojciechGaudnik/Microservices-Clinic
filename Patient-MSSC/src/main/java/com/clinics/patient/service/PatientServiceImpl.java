package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.EditPatientDTO;
import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.PatientNotFoundException;
import com.clinics.patient.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService{
    final private PatientRepository patientRepository;
    final private ModelMapper modelMapper;
    final private RestTemplate restTemplate;
    final private PatientClient patientClient;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, RestTemplate restTemplate, PatientClient patientClient) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.patientClient = patientClient;
    }

    @Override
    public PatientRegisterResponseDTO addPatient(RegisterPatientDTO registerPatientDTO, HttpServletRequest request) {
        String url = String.format("http://auth/auth/users/%s", registerPatientDTO.getPatientUUID());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
        HttpEntity<String> requestEntity = new HttpEntity<>("Empty Request", httpHeaders);
        try {
            ResponseEntity<Void> responseFromAuth = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
        } catch (Exception e) {
            throw new NoSuchElementException("There is no such user in AUTH or something else went wrong");
        }
        var patient = modelMapper.map(registerPatientDTO, Patient.class);
        patientRepository.save(patient);
        return modelMapper.map(patient, PatientRegisterResponseDTO.class);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findByUuid(UUID UUID) {
        Optional<Patient> patient = patientRepository.findByPatientUUID(UUID);

        if(patient.isPresent()){
            return patient.get();
        }else{
            throw new PatientNotFoundException(UUID);
        }
    }

    @Override
    public Optional<Patient> findById(Long ID) {
        return patientRepository.findById(ID);
    }

    @Override
    public void deleteByUuid(UUID UUID) {
        patientRepository.deleteByPatientUUID(UUID);
    }

    @Override
    public void editPatient(UUID patientUUID, EditPatientDTO patient) {
        Optional<Patient> existingPatient = patientRepository.findByPatientUUID(patientUUID);

        if(existingPatient.isEmpty()){
            throw new PatientNotFoundException(patientUUID);
        }

        existingPatient.ifPresent(thePatient-> {
            thePatient.setPesel(patient.getPesel());
            thePatient.setFirstName(patient.getFirstName());
            thePatient.setLastName(patient.getLastName());
            thePatient.setPhotoUrl(patient.getPhotoUrl());
        });
    }

    @Override
    public List<Visit> findAllVisits(UUID UUID) {
        Optional<Patient> patient = patientRepository.findByPatientUUID(UUID);

        if(patient.isPresent()) {
            return patient.get().getVisits();
        }else{
            throw new PatientNotFoundException(UUID);
        }
    }
}
