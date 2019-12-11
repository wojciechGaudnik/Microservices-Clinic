package com.clinicsmicroservices.doctor.controllers;

import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.services.DoctorService;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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
	public Doctor getFirstUser(){
		log.debug(ConsoleColors.YELLOW + "Message from Controller" + ConsoleColors.RESET);
		return doctorService.getDoctorByID(1L).get();
	}
}
