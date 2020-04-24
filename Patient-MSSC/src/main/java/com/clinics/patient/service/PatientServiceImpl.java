package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.EditPatientDTO;
import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.PatientNotFoundException;
import com.clinics.patient.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
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
        try{
            patientClient.activatePatientInAuth(registerPatientDTO, request);
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
    public Patient findByUuid(UUID patientUUID) {
        Optional<Patient> patient = patientRepository.findByPatientUUID(patientUUID);

        if(patient.isPresent()){
            return patient.get();
        }else{
            throw new PatientNotFoundException(patientUUID);
        }
    }

    @Override
    public Optional<Patient> findById(Long ID) {
        return patientRepository.findById(ID);
    }

    @Override
    public void deleteByUuid(UUID patientUUID) {
        //TODO catch exception
        String uri = String.format("http://auth/auth/users/%s", patientUUID);
        restTemplate.delete(uri);

        patientRepository.deleteByPatientUUID(patientUUID);
    }

    @Override
    public void editPatient(UUID patientUUID, EditPatientDTO editPatientDTO) {
        Optional<Patient> existingPatient = patientRepository.findByPatientUUID(patientUUID);

        existingPatient.ifPresentOrElse(
                patient -> {
                    modelMapper.map(editPatientDTO, patient);
                    patientRepository.save(patient);
                },
                () -> {
                    throw new PatientNotFoundException(patientUUID);
                }
        );
    }

    @Override
    public List<Visit> findAllVisits(UUID patientUUID) {
        Optional<Patient> patient = patientRepository.findByPatientUUID(patientUUID);

        if(patient.isPresent()) {
            return patient.get().getVisits();
        }else{
            throw new PatientNotFoundException(patientUUID);
        }
    }
}
