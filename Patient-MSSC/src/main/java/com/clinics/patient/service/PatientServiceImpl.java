package com.clinics.patient.service;

import com.clinics.common.DTO.request.RegisterPatientDTO;
import com.clinics.common.DTO.response.PatientRegisterResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
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
        String url = String.format("http://auth/auth/users/%s", registerPatientDTO.getUuid());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
        HttpEntity<String> testName = new HttpEntity<>("Empty Request", httpHeaders);
        try {
            ResponseEntity<Void> responseFromAuth = restTemplate.exchange(url, HttpMethod.PUT, testName, Void.class);
        } catch (Exception e) {
            throw new NoSuchElementException("There is no such patient in AUTH");
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
        return patientRepository.findByUuid(UUID);
    }

    @Override
    public Optional<Patient> findById(Long ID) {
        return patientRepository.findById(ID);
    }

    @Override
    public void deleteById(Long ID) {
        patientRepository.deleteById(ID);
    }

    @Override
    public Patient editPatient(Patient patient) {

        patientValid(patient);
        Optional<Patient> existingPatient = patientRepository.findById(patient.getId());

        if(existingPatient.isPresent())
        {
            //TODO change patient data
            existingPatient.get().setPesel(patient.getPesel());
            return patientRepository.save(existingPatient.get());
        }else{
            return null;
        }
    }

    @Override
    public List<Visit> findAllVisits(UUID UUID) {
        Patient patient = patientRepository.findByUuid(UUID);
        return patient.getVisits();
    }

    private void patientValid(Patient patient){
        // TODO patient data validation
        // TODO Throw exception if f.ex pesel is wrong
    }


}
