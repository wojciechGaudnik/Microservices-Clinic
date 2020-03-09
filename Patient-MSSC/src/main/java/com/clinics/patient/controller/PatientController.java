package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.PatientService;
import com.clinics.patient.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patient") //todo patients
public class PatientController {
    final private PatientService patientService;
    final private VisitService visitService;

    public PatientController(PatientService patientService, VisitService visitService) {
        this.patientService = patientService;
        this.visitService = visitService;
    }

    @GetMapping(path = "")
    public List<Patient> list() {
        return patientService.findAll();
    }

    @GetMapping(path = "/{UUID}")
    public Patient getPatientByUUID(@PathVariable UUID UUID){
        return patientService.findByUuid(UUID);
    }

    @PostMapping(path = "/")
    public ResponseEntity<PatientRegisterResponseDTO> registerPatient(@RequestBody RegisterPatientDTO registerPatientDTO, HttpServletRequest request){
        return ResponseEntity.status(201).body(patientService.addPatient(registerPatientDTO, request));
    }

    @PutMapping
    public Patient editPatient(@RequestBody Patient patient) {
        return patientService.editPatient(patient);
    }

    @GetMapping(path = "/{UUID}/visits")
    public List<Visit> getAllVisits(@PathVariable UUID UUID) {
        return patientService.findAllVisits(UUID);
    }

    @DeleteMapping(path = "/{UUID}")
    public void cancelVisit(@PathVariable UUID UUID){
        visitService.deleteByUuid(UUID);
    }
}