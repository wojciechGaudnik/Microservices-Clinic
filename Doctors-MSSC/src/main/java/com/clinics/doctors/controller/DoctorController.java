package com.clinics.doctors.controller;

import com.clinics.common.DTO.request.RegisterDoctorDTO;
import com.clinics.common.DTO.response.DoctorResponseDTO;
import com.clinics.doctors.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	final DoctorService doctorService;
	final RestTemplate restTemplate;

	@Autowired
	public DoctorController(DoctorService doctorService, RestTemplate restTemplate) {
		this.doctorService = doctorService;
		this.restTemplate = restTemplate;
	}

	@GetMapping
	public ResponseEntity<String> getDefault(){
		return ResponseEntity.ok().body("{\"message\":\"Hello world from doctor\"}");
	}

	@PostMapping
	public ResponseEntity<DoctorResponseDTO> registerDoctor(@Valid @RequestBody RegisterDoctorDTO registerDoctorDTO) {
		return ResponseEntity.status(201).body(doctorService.saveDoctor(registerDoctorDTO));
	}

	@GetMapping(path = "/{UUID}")
	public ResponseEntity<DoctorResponseDTO> getDoctorByUUID(@PathVariable UUID UUID){
		return ResponseEntity.ok().body(doctorService.getDoctorByUUID(UUID));
	}

	@GetMapping(path = "/test/{text}")
	public ResponseEntity<String> getTestFromAuth(@PathVariable String text){
		return ResponseEntity.ok().body(restTemplate.getForObject("http://auth/users/" + text, String.class));
	}
}
