package com.clinicsmicroservices.doctor.controllers;

import com.clinicsmicroservices.doctor.services.DoctorService;
import com.clinicsmicroservices.doctor.shared.Patient;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
		RestTemplate restTemplate = new RestTemplate();

		final String uri = "http://localhost:8081/patient/test/";
		log.debug(ConsoleColors.YELLOW + restTemplate.getForObject(uri, String.class));
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		List<Object> objectList = new ArrayList<>();
		objectList.add(doctorService.getDoctorByID(1L).get());
		objectList.add(restTemplate.getForObject(uri, Patient.class));
		return objectList;
	}
}
