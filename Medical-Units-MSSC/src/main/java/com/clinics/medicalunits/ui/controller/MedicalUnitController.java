package com.clinics.medicalunits.ui.controller;

import com.clinics.common.DTO.request.outer.medicalUnit.RegisterMedicalUnitDTO;
import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.medicalunits.ui.service.MedicalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/medical-units")
public class MedicalUnitController {

	final MedicalUnitService medicalUnitService;
	final RestTemplate restTemplate;

	@Autowired
	public MedicalUnitController(MedicalUnitService medicalUnitService, RestTemplate restTemplate) {
		this.medicalUnitService = medicalUnitService;
		this.restTemplate = restTemplate;
	}

	@GetMapping
	public ResponseEntity<List<MedicalUnitResponseDTO>> getAllMedicalUnits(){
		return ResponseEntity.ok().body(medicalUnitService.getAll());
	}

	@GetMapping(path = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> getMedicalUnitByUUID(@PathVariable UUID medicalUnitUUID){
		return ResponseEntity.ok().body(medicalUnitService.getByUUID(medicalUnitUUID));
	}

	@PostMapping
	public ResponseEntity<MedicalUnitResponseDTO> add(@Valid @RequestBody RegisterMedicalUnitDTO registerMedicalUnitDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicalUnitService.save(registerMedicalUnitDTO));
	}




	@GetMapping(path = "/test")
	public ResponseEntity<String> getDefault(){
		return ResponseEntity.ok().body("{\"message\":\"Hello world from medical unit\"}");
	}

	@GetMapping(path = "/test/{text}")
	public ResponseEntity<String> getTestFromAuth(@PathVariable String text){
		return ResponseEntity.ok().body(restTemplate.getForObject("http://auth/auth/test/" + text, String.class));
	}
}
