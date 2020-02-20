package com.clinics.patient.service;

import com.clinics.common.DTO.request.RegisterPatientDTO;
import com.clinics.common.DTO.response.PatientRegisterResponseDTO;
import com.clinics.patient.entity.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    PatientRegisterResponseDTO addPatient(RegisterPatientDTO registerPatientDTO);
    List<Patient> findAll();
    Patient findByUuid(UUID UUID);
    Optional<Patient> findById(Long ID);
    void deleteById(Long ID);
    Patient editPatient(Patient patient);
}
