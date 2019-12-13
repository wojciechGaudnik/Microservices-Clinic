package com.clinicsmicroservices.doctor.controllers;

import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.services.DoctorService;
import com.clinicsmicroservices.doctor.shared.Patient;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping("/test")
	@ResponseBody
	public List<Object> getFirstUser(){

		//todo exception handling pattern
//		Doctor doctor = null;
//		Optional<Doctor> optionalDoctor = Optional.of(doctor);
//		log.error("test");
//		int id = 666;
//		if (doctor == null) {
//			throw new UserNotFoundException("id-" + id);
//		}

		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8081/patient/test/";
		log.debug(ConsoleColors.YELLOW + restTemplate.getForObject(uri, String.class));
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		List<Object> objectList = new ArrayList<>();
		objectList.add(doctorService.getDoctorByID(1L).get());
		objectList.add(restTemplate.getForObject(uri, Patient.class));
		return objectList;
	}

	@GetMapping("/hateoas")
	public EntityModel<Doctor> getHateoasFormatTest(){
		//todo HATEOAS pattern
		Doctor doctorHATEOAS = Doctor.builder().firstName("Hateoas doctor").build();
		EntityModel<Doctor> resource = new EntityModel<>(doctorHATEOAS);
		WebMvcLinkBuilder linkToDoctor = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFirstUser());
		resource.add(linkToDoctor.withRel("First user named by Me"));
		return resource;
	}


	@PostMapping("/add")
	public ResponseEntity<Object> addDoctor(@Valid @RequestBody Doctor doctor) {
		log.debug(doctor.toString());
		Doctor savedDoctor = doctor;
		doctorService.add(doctor);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDoctor.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
