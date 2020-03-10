package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.medicalUnit.AddMedicalUnitUUID_DTO;
import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.doctors.ui.service.DoctorMedicalUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/medical-units")
public class DoctorMedicalUnitController {


	final private DoctorMedicalUnitsService doctorMedicalUnitsService;

	public DoctorMedicalUnitController(
			DoctorMedicalUnitsService doctorMedicalUnitsService) {
		this.doctorMedicalUnitsService = doctorMedicalUnitsService;
	}

	//todo 1) doctor return only UUID   front--->   doctor-mssc--->     response UUID--->       front--->  medical-unit-mssc--->  front
	//todo 2) doctor return All         front--->   doctor-mssc--->     medical-unit-mssc--->   doctor---> front
	//todo 1) + crossing zuul

	@GetMapping
	public ResponseEntity<List<MedicalUnitResponseDTO>> getDoctorsMedicalUnits(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(doctorMedicalUnitsService.getAll(doctorUUID));
	}

	@GetMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> getDoctorsMedicalUnit(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID medicalUnitUUID
	){
		return ResponseEntity.ok().body(doctorMedicalUnitsService.get(doctorUUID, medicalUnitUUID));
	}

	@PostMapping
	public ResponseEntity<MedicalUnitResponseDTO> add(
			@Valid @RequestBody AddMedicalUnitUUID_DTO addMedicalUnitUUIDDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorMedicalUnitsService.save(addMedicalUnitUUIDDTO, doctorUUID));
	}

	@DeleteMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID medicalUnitUUID,
			@PathVariable UUID doctorUUID) {
		doctorMedicalUnitsService.delete(doctorUUID, medicalUnitUUID);
		return ResponseEntity.ok().build();
	}
}
