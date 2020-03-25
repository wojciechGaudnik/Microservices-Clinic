package com.clinics.doctors.ui.client;

import com.clinics.common.DTO.response.outer.PatientResponseDTO;
import com.clinics.doctors.ui.service.JPAimpl.DoctorPatientClientImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/patients")
public class DoctorPatientController {

	final private DoctorPatientClientImpl doctorPatientClientImpl;

	public DoctorPatientController(DoctorPatientClientImpl doctorPatientClientImpl) {
		this.doctorPatientClientImpl = doctorPatientClientImpl;
	}

	@GetMapping
	public ResponseEntity<List<PatientResponseDTO>> getAllDoctorPatients(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(doctorPatientClientImpl.getAll(doctorUUID));
	}

	@GetMapping(value = "/{patientUUID}")
	public ResponseEntity<PatientResponseDTO> getDoctorPatient(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID patientUUID
	){
		return ResponseEntity.ok().body(doctorPatientClientImpl.get(doctorUUID, patientUUID));
	}

	@PostMapping(value = "/{patientUUID}")
	public ResponseEntity<PatientResponseDTO> addPatientIntoDoctor(
			@PathVariable UUID patientUUID,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorPatientClientImpl.save(patientUUID, doctorUUID));
	}

	@DeleteMapping(value = "/{patientUUID}")
	public ResponseEntity<Void> removePatientFromDoctor(
			@PathVariable UUID patientUUID,
			@PathVariable UUID doctorUUID) {
		doctorPatientClientImpl.delete(doctorUUID, patientUUID);
		return ResponseEntity.ok().build();
	}
}