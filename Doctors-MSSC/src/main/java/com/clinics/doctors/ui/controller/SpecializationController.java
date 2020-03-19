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
@RequestMapping(value = "/specializations")
public class SpecializationController {

	final private SpecializationServiceImpl specializationServiceImpl;

	public SpecializationController(SpecializationServiceImpl specializationServiceImpl) {
		this.specializationServiceImpl = specializationServiceImpl;
	}

	@GetMapping
	public ResponseEntity<List<SpecializationResponseDTO>> getAll(){
		return ResponseEntity.ok().body(specializationServiceImpl.getAllSpecializations());
	}

	@GetMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> getByUUID(
			@PathVariable UUID specializationUUID){
		return ResponseEntity.ok().body(specializationServiceImpl.getSpecializationByUUID(specializationUUID));
	}

	@PostMapping
	public ResponseEntity<SpecializationResponseDTO> add (
			@Valid @RequestBody SpecializationDTO specializationDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationServiceImpl.saveSpecialization(specializationDTO));
	}

	@PatchMapping(value = "/{specializationUUID}")
	public ResponseEntity<Void> edit(
			@Valid @RequestBody SpecializationDTO specializationDTO,
			@PathVariable UUID specializationUUID) {
		specializationServiceImpl.editSpecialization(specializationDTO, specializationUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{specializationUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID specializationUUID) {
		specializationServiceImpl.deleteSpecialization(specializationUUID);
		return ResponseEntity.ok().build();
	}
}
