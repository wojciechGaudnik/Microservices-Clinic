package com.clinics.doctors.controllers;

import com.clinics.doctors.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping(path = "{UUID}")
	public ResponseEntity<String> getDoctorByUUID(@PathVariable String UUID){
		return ResponseEntity.ok().body(doctorService.getDoctorByUUID(UUID));
	}
}
