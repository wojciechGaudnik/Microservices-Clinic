package com.clinics.doctors.controller;

import com.clinics.doctors.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	public ResponseEntity<String> getDefault(){
		return ResponseEntity.ok().body("{\"message\":\"Hello world from doctor\"}");
	}

	@GetMapping(path = "/{UUID}")
	public ResponseEntity<String> getDoctorByUUID(@PathVariable UUID UUID){
		return ResponseEntity.ok().body(doctorService.getDoctorByUUID(UUID) + " <--- my note 12.2 21.16");
	}

	@GetMapping(path = "/id/{ID}")
	public ResponseEntity<String> getDoctorByID(@PathVariable Long ID){
		return ResponseEntity.ok().body(doctorService.getDoctorByID(ID));
	}

	@GetMapping(path = "/test/{text}")
	public ResponseEntity<String> getTestFromAuth(@PathVariable String text){

		log.error("test log");

		return ResponseEntity.ok().body(restTemplate.getForObject("http://auth/users/" + text, String.class));
	}
}
