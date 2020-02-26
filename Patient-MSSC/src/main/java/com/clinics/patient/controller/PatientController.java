package com.clinics.patient.controller;

import com.clinics.common.DTO.request.RegisterPatientDTO;
import com.clinics.common.DTO.response.PatientRegisterResponseDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    final private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

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
    } //Do we need this if we should not expose DB id ?

    @PostMapping(path = "/")
    public ResponseEntity<PatientRegisterResponseDTO> registerPatient(@RequestBody RegisterPatientDTO registerPatientDTO, HttpServletRequest request){
        return ResponseEntity.status(201).body(patientService.addPatient(registerPatientDTO, request));
    }

    @DeleteMapping(path = "/id/{ID}")
    public void deletePatientByID(@PathVariable Long ID){
        patientService.deleteById(ID);
    }

    @PutMapping
    public Patient editPatient(@RequestBody Patient patient) {
        return patientService.editPatient(patient);
    }

    //wszystkie wizyty dla pacjenta
    @GetMapping(path = "/{UUID}/visits")
    public List<Visit> getAllVisits(@PathVariable UUID UUID) {
        return patientService.findAllVisits(UUID);
    }
}