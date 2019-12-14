package com.clinicsmicroservices.doctor.controllers;

import com.clinicsmicroservices.doctor.configuration.Configuration;
import com.clinicsmicroservices.doctor.configuration.DoctorConfiguration;
import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.services.DoctorService;
import com.clinicsmicroservices.doctor.shared.Patient;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/doctors")
public class DoctorController {

	private final DoctorService doctorService;
	private final Configuration configuration;

	public DoctorController(DoctorService doctorService, Configuration configuration) {
		this.doctorService = doctorService;
		this.configuration = configuration;
	}

	@GetMapping("/test")
	@ResponseBody
	public List<Object> getFirstUser() {

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
		log.debug(ConsoleColors.YELLOW + "Message from Controller New test" + ConsoleColors.RESET);
		List<Object> objectList = new ArrayList<>();
		objectList.add(doctorService.getDoctorByID(1L).get());
		objectList.add(restTemplate.getForObject(uri, Patient.class));
		return objectList;
	}

	@GetMapping("/hateoas")
	public EntityModel<Doctor> getHateoasFormatTest() {
		//todo HATEOAS pattern
		Doctor doctorHATEOAS = Doctor.builder().firstName("Hateoas doctor").build();
		EntityModel<Doctor> resource = new EntityModel<>(doctorHATEOAS);
		WebMvcLinkBuilder linkToDoctor = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFirstUser());
		resource.add(linkToDoctor.withRel("First user named by Me"));
		return resource;
	}

//	@GetMapping("/{id}")
//	public Doctor getOverride() {
//		//todo override data-rest
//		Doctor doctorHATEOAS = Doctor.builder().firstName("Hateoas doctor").build();
//		EntityModel<Doctor> resource = new EntityModel<>(doctorHATEOAS);
//		WebMvcLinkBuilder linkToDoctor = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFirstUser());
//		resource.add(linkToDoctor.withRel("First user named by Me"));
//		return new Doctor().toBuilder().firstName("test from override").build();
//	}


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

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeDoctor(){
		Doctor doctor = doctorService.getDoctorByID(1L).get();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("Id", "uuid");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DoctorFilter", filter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(doctor);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;

	}

	@GetMapping("/configuration")
	public DoctorConfiguration getDoctorConf(){

		log.error("test");
		log.error(String.valueOf(configuration.getBq666()));
		return new DoctorConfiguration(configuration.getBq666());
	}
}
