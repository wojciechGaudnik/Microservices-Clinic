package com.clinics.medicalunits.ui.controller;

import com.clinics.common.DTO.request.outer.RegisterMedicalUnitDTO;
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

@Controller
@RequestMapping(value = "/medical-unit")
public class MedicalUnitController {

	final MedicalUnitService medicalUnitService;
	final RestTemplate restTemplate;

	@Autowired
	public MedicalUnitController(MedicalUnitService medicalUnitService, RestTemplate restTemplate) {
		this.medicalUnitService = medicalUnitService;
		this.restTemplate = restTemplate;
	}

	@GetMapping
	public ResponseEntity<List<MedicalUnitResponseDTO>> getMedicalUnitByUUID(){
		return ResponseEntity.ok().body(medicalUnitService.getAll());
	}

	@PostMapping
	public ResponseEntity<Void> add(@Valid @RequestBody RegisterMedicalUnitDTO registerMedicalUnitDTO) {
		medicalUnitService.save(registerMedicalUnitDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
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
