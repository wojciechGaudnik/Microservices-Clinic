package com.clinics.patient.service;

import com.clinics.patient.entity.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    void savePatient(Patient patient);
    List<Patient> findAll();
    Patient findByUuid(UUID UUID);
    Optional<Patient> findById(Long ID);
    void deleteById(Long ID);
    Patient editPatient(Patient patient);
}
