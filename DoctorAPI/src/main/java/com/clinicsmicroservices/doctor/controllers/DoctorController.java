package com.clinicsmicroservices.doctor.controllers;

import com.clinicsmicroservices.doctor.exceptions.UserNotFoundException;
import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.services.DoctorService;
import com.clinicsmicroservices.doctor.shared.Patient;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
		Doctor doctor = null;
//		Optional<Doctor> optionalDoctor = Optional.of(doctor);
		log.error("test");
		int id = 666;
		if (doctor == null) {
			throw new UserNotFoundException("id-" + id);
		}


		RestTemplate restTemplate = new RestTemplate();

		final String uri = "http://localhost:8081/patient/test/";
		log.debug(ConsoleColors.YELLOW + restTemplate.getForObject(uri, String.class));
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		List<Object> objectList = new ArrayList<>();
		objectList.add(doctorService.getDoctorByID(1L).get());
		objectList.add(restTemplate.getForObject(uri, Patient.class));
		return objectList;
	}

	@PostMapping("/doctor")
	public ResponseEntity<Object> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctor;
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDoctor.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
