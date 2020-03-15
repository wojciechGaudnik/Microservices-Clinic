package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.doctors.ui.service.DoctorMedicalUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/medical-units")
public class DoctorMedicalUnitController {


	final private DoctorMedicalUnitService doctorMedicalUnitService;

	public DoctorMedicalUnitController(
			DoctorMedicalUnitService doctorMedicalUnitService) {
		this.doctorMedicalUnitService = doctorMedicalUnitService;
	}

	//todo 1) doctor return only UUID   front--->   doctor-mssc--->     response UUID--->       front--->  medical-unit-mssc--->  front
	//todo 2) doctor return All         front--->   doctor-mssc--->     medical-unit-mssc--->   doctor---> front
	//todo 1) + crossing zuul

	@GetMapping
	public ResponseEntity<List<MedicalUnitResponseDTO>> getDoctorsMedicalUnits(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(doctorMedicalUnitService.getAll(doctorUUID));
	}

	@GetMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> getDoctorsMedicalUnit(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID medicalUnitUUID
	){
		return ResponseEntity.ok().body(doctorMedicalUnitService.get(doctorUUID, medicalUnitUUID));
	}

	@PostMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> add(
			@PathVariable UUID medicalUnitUUID,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorMedicalUnitService.save(medicalUnitUUID, doctorUUID));
	}

	@DeleteMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID medicalUnitUUID,
			@PathVariable UUID doctorUUID) {
		doctorMedicalUnitService.delete(doctorUUID, medicalUnitUUID);
		return ResponseEntity.ok().build();
	}
}
