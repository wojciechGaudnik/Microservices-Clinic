package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.AddEditSpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
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
@RequestMapping(value = "/specialization")
public class SpecializationController {

	final private SpecializationService specializationService;

	public SpecializationController(SpecializationService specializationService) {
		this.specializationService = specializationService;
	}

	@GetMapping
	public ResponseEntity<List<SpecializationResponseDTO>> getAll(){
		return ResponseEntity.ok().body(specializationService.getAll());
	}

	@GetMapping(value = "/{specializationUUID}")
	public ResponseEntity<SpecializationResponseDTO> getByUUID(@PathVariable UUID specializationUUID){
		return ResponseEntity.ok().body(specializationService.getByUUID(specializationUUID));
	}

	@PostMapping
	public ResponseEntity<SpecializationResponseDTO> add (
			@Valid @RequestBody AddEditSpecializationDTO addEditSpecializationDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(specializationService.save(addEditSpecializationDTO));
	}

	@PatchMapping(value = "/{specializationUUID}")
	public ResponseEntity<Void> edit(
			@Valid @RequestBody AddEditSpecializationDTO addEditSpecializationDTO,
			@PathVariable UUID specializationUUID) {
		specializationService.edit(addEditSpecializationDTO, specializationUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{specializationUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID specializationUUID) {
		specializationService.delete(specializationUUID);
		return ResponseEntity.ok().build();
	}


}
