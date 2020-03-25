package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.EditPatientDTO;
import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.PatientService;
import com.clinics.patient.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    final private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> list() {
        return patientService.findAll();
    }

    @GetMapping(path = "/{patientUUID}")
    public Patient getPatientByUUID(@PathVariable UUID patientUUID){
        return patientService.findByUuid(patientUUID);
    }

    @PostMapping
    public ResponseEntity<PatientRegisterResponseDTO> registerPatient(@RequestBody RegisterPatientDTO registerPatientDTO, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(registerPatientDTO, request));
    }

    @PatchMapping(path = "/{patientUUID}")
    public void editPatient(@PathVariable UUID patientUUID, @RequestBody EditPatientDTO patient) {
        patientService.editPatient(patientUUID, patient);
    }

    @GetMapping(path = "/{patientUUID}/visits")
    public List<Visit> getAllVisits(@PathVariable UUID patientUUID) {
        return patientService.findAllVisits(patientUUID);
    }
}