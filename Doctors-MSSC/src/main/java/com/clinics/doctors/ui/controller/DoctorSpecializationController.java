package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.SpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
import com.clinics.doctors.ui.service.JPAimpl.SpecializationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/specializations")
public class DoctorSpecializationController {

	final private SpecializationServiceImpl specializationServiceImpl;

	public DoctorSpecializationController(
			SpecializationServiceImpl specializationServiceImpl) {
		this.specializationServiceImpl = specializationServiceImpl;
	}

	@GetMapping
	public ResponseEntity<List<SpecializationResponseDTO>> getAllDoctorSpecializations(
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.ok().body(specializationServiceImpl.getDoctorSpecializations(doctorUUID));
	}

	@GetMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> getDoctorSpecialization(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID specializationUUID) {
		return ResponseEntity.ok().body(specializationServiceImpl.getDoctorSpecialization(doctorUUID, specializationUUID));
	}

	@PostMapping
	public ResponseEntity<SpecializationResponseDTO> addExistingSpecializationIntoDoctor(
			@Valid @RequestBody SpecializationDTO specializationDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationServiceImpl.saveSpecializationIntoDoctor(doctorUUID, specializationDTO));
	}

	@PostMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> addExistingSpecializationIntoDoctor(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID specializationUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationServiceImpl.saveExistingSpecializationIntoDoctor(doctorUUID, specializationUUID));
	}

//	@PatchMapping(value = "/{specializationUUID}")
//	public ResponseEntity<Void> edit(
//			@Valid @RequestBody AddEditSpecializationDTO addEditCalendarDTO,
//			@PathVariable UUID specializationUUID) {
//		specializationService.edit(addEditCalendarDTO, specializationUUID);
//		return ResponseEntity.ok().build();
//	}
//

	@DeleteMapping(value = "/{specializationUUID}")
	public ResponseEntity<Void> removeSpecializationFromDoctor(
			@PathVariable UUID specializationUUID,
			@PathVariable UUID doctorUUID) {
		specializationServiceImpl.removeSpecializationFromDoctor(doctorUUID, specializationUUID);
		return ResponseEntity.ok().build();
	}
}
