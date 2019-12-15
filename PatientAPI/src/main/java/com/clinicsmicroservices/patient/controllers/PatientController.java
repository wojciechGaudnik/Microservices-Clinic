package com.clinicsmicroservices.patient.controllers;

import com.clinicsmicroservices.patient.model.Patient;
import com.clinicsmicroservices.patient.services.PatientService;
import com.clinicsmicroservices.patient.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/patients")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/test1")
	@ResponseBody
	public Patient getFirstUser(){
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		return patientService.getPatientById(1L).get();
	}

	@GetMapping("/test")
	public EntityModel<Patient> getHateoasFormatTest() {
		//todo HATEOAS pattern
		Patient doctorHATEOAS = Patient.builder().firstName("Hateoas patient").build();
		EntityModel<Patient> resource = new EntityModel<>(doctorHATEOAS);
		WebMvcLinkBuilder linkToDoctor = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFirstUser());
		resource.add(linkToDoctor.withRel("First user named by Me"));
		return resource;
	}
}
