package com.clinics.doctors.controllers;

import com.clinics.doctors.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping
//			(produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDefault(){
		return "{\"message\":\"Hello world from doctor\"}";
//		return ResponseEntity.ok().body("{\"message\":\"Hello world from doctor\"}");
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

	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
		response.setHeader("Content-Type", "application/json");
		response.setHeader("6666", "666666");
	}

}
