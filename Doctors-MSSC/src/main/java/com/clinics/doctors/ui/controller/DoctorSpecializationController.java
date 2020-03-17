package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.AddEditCalendarDTO;
import com.clinics.common.DTO.request.outer.doctor.AddEditSpecializationDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
import com.clinics.doctors.ui.service.CalendarService;
import com.clinics.doctors.ui.service.SpecializationService;
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
@RequestMapping(value = "/doctors/{doctorUUID}/specializations")
public class DoctorSpecializationController {

	final private SpecializationService specializationService;

	public DoctorSpecializationController(
			SpecializationService specializationService) {
		this.specializationService = specializationService;
	}

	@GetMapping
	public ResponseEntity<List<SpecializationResponseDTO>> getDoctorSpecializations(
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.ok().body(specializationService.getDoctorSpecializations(doctorUUID));
	}

	@GetMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> getDoctorSpecialization(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID specializationUUID
			) {
		return ResponseEntity.ok().body(specializationService.getDoctorSpecialization(doctorUUID, specializationUUID));
	}

	@PostMapping
	public ResponseEntity<SpecializationResponseDTO> add(
			@Valid @RequestBody AddEditSpecializationDTO addEditSpecializationDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationService.save(addEditSpecializationDTO, doctorUUID));
	}

	@PostMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> add(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID specializationUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationService.save(doctorUUID, specializationUUID));
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
	public ResponseEntity<Void> del(
			@PathVariable UUID specializationUUID,
			@PathVariable UUID doctorUUID) {
		specializationService.delete(specializationUUID, doctorUUID);
		return ResponseEntity.ok().build();
	}
}
