package com.clinics.doctors.controllers;

import com.clinics.doctors.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping(path = "{UUID}")
	public ResponseEntity<String> getDoctorByUUID(@PathVariable UUID UUID){
		return ResponseEntity.ok().body(doctorService.getDoctorByUUID(UUID));
	}

	@GetMapping(path = "/id/{ID}")
	public ResponseEntity<String> getDoctorByID(@PathVariable Long ID){
		return ResponseEntity.ok().body(doctorService.getDoctorByID(ID));
	}
}
