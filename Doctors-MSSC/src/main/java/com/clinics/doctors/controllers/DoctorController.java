package com.clinics.doctors.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	@GetMapping(path = "{doctorId}")
	public ResponseEntity<String> getDoctor(@PathVariable String doctorId){
		return ResponseEntity.ok().body("Hello from Doctor + doctorId" + doctorId);
	}
}
