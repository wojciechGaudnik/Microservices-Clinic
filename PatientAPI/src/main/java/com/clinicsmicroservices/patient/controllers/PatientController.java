package com.clinicsmicroservices.patient.controllers;

import com.clinicsmicroservices.patient.model.Patient;
import com.clinicsmicroservices.patient.services.PatientService;
import com.clinicsmicroservices.patient.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/test")
	@ResponseBody
	public Patient getFirstUser(){
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		return patientService.getPatientById(1L).get();
	}
}
