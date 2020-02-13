package com.clinics.patient.controller;

import com.clinics.patient.entity.Patient;
import com.clinics.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/test")
    public String getTest(){
        return "Hello from patient";
    }

    @GetMapping(path = "/{UUID}")
    public Patient getPatientByUUID(@PathVariable UUID UUID){
        return patientRepository.findByUuid(UUID);
    }

    @GetMapping(path = "/id/{ID}")
    public Optional<Patient> getPatientByID(@PathVariable Long ID){
        return patientRepository.findById(ID);
    }

    @DeleteMapping(path = "/id/{ID}")
    public void deletePatientByID(@PathVariable Long ID){
        patientRepository.deleteById(ID);
    }
}

