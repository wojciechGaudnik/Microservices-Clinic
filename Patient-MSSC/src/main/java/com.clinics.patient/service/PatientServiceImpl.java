package com.clinics.patient.service;


import com.clinics.patient.entity.Patient;
import com.clinics.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
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
    public Patient editPatient(Patient patientToChange, Patient patient) {
        //TODO
        savePatient(patientToChange);
        return patientToChange;
    }
}
