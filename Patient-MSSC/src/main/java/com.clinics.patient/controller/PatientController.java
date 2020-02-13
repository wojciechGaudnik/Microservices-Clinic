package com.clinics.patient.controller;

import com.clinics.patient.entity.Patient;
import com.clinics.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/test")
    public String getTest(){
        return "Hello from patient";
    }

    @GetMapping(path = "/patients")
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

    @PutMapping(path = "/{ID}")
    public Patient editPatient(@PathVariable Long ID, @RequestBody Patient patient) {
        Patient patientToChange = getPatientByID(ID).get();
        return patientService.editPatient(patientToChange, patient);
    }
}