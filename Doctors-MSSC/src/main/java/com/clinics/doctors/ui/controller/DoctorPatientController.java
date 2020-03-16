package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.response.outer.PatientResponseDTO;
import com.clinics.doctors.ui.service.DoctorPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/patients")
public class DoctorPatientController {

	final private DoctorPatientService doctorPatientService;

	public DoctorPatientController(DoctorPatientService doctorPatientService) {
		this.doctorPatientService = doctorPatientService;
	}

	@GetMapping
	public ResponseEntity<List<PatientResponseDTO>> getDoctorsPatients(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(doctorPatientService.getAll(doctorUUID));
	}

	@GetMapping(value = "/{patientUUID}")
	public ResponseEntity<PatientResponseDTO> getDoctorsMedicalUnit(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID patientUUID
	){
		return ResponseEntity.ok().body(doctorPatientService.get(doctorUUID, patientUUID));
	}

	@PostMapping(value = "/{patientUUID}")
	public ResponseEntity<PatientResponseDTO> add(
			@PathVariable UUID patientUUID,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorPatientService.save(patientUUID, doctorUUID));
	}

	@DeleteMapping(value = "/{patientUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID patientUUID,
			@PathVariable UUID doctorUUID) {
		doctorPatientService.delete(doctorUUID, patientUUID);
		return ResponseEntity.ok().build();
	}
}