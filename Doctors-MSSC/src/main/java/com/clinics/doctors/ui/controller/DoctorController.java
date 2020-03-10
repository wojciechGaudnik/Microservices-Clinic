package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.EditDoctorDTO;
import com.clinics.common.DTO.request.outer.doctor.RegisterDoctorDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.doctors.ui.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
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
	public ResponseEntity<List<DoctorResponseDTO>> getAll(@PathVariable UUID uuid){
		return ResponseEntity.ok().body(doctorService.getAll());
	}


	@GetMapping(path = "/{uuid}")
	public ResponseEntity<DoctorResponseDTO> getByUUID(@PathVariable UUID uuid){
		return ResponseEntity.ok().body(doctorService.getByUUID(uuid));
	}

	@PostMapping
	public ResponseEntity<DoctorResponseDTO> add(
			@Valid @RequestBody RegisterDoctorDTO registerDoctorDTO,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.save(registerDoctorDTO, request));
	}

	@PatchMapping(path = "/{uuid}")
	public ResponseEntity<Void> edit(
			@Valid @RequestBody EditDoctorDTO editDoctorDTO,
			@PathVariable UUID uuid,
			HttpServletRequest request) {
		doctorService.edit(editDoctorDTO, uuid, request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{uuid}")
	public ResponseEntity<Void> delete(
			@PathVariable UUID uuid) {
		doctorService.delete(uuid);
		return ResponseEntity.ok().build();
	}




	@GetMapping(path = "/test")
	public ResponseEntity<String> getDefault(){
		return ResponseEntity.ok().body("{\"message\":\"Hello world from doctor\"}");
	}

	@GetMapping(path = "/test/{text}")
	public ResponseEntity<String> getTestFromAuth(@PathVariable String text){
		return ResponseEntity.ok().body(restTemplate.getForObject("http://auth/auth/test/" + text, String.class));
	}
}
