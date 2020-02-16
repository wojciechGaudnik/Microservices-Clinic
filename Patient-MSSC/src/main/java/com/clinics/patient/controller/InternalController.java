package com.clinics.patient.controller;

import com.clinics.patient.entity.Patient;
import com.clinics.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patient")
public class InternalController {

    @Autowired
    PatientService patientService;

    @GetMapping(path = "")
    public List<Patient> list() {
        return patientService.findAll();
    }

    @GetMapping(path = "/{UUID}")
    public Patient getPatientByUUID(@PathVariable UUID UUID){
        return patientService.findByUuid(UUID);
    }

    @GetMapping(path = "/id/{ID}")
    public Optional<Patient> getPatientByID(@PathVariable Long ID){
        return patientService.findById(ID);
    }

    @DeleteMapping(path = "/id/{ID}")
    public void deletePatientByID(@PathVariable Long ID){
        patientService.deleteById(ID);
    }

    @PutMapping
    public Patient editPatient(@RequestBody Patient patient) {
        return patientService.editPatient(patient);
    }
}