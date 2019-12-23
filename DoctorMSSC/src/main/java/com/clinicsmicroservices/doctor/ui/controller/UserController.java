package com.clinicsmicroservices.doctor.ui.controller;

import com.clinicsmicroservices.doctor.exceptions.UserServiceException;
import com.clinicsmicroservices.doctor.ui.model.request.UpdateUserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;
import com.clinicsmicroservices.doctor.ui.service.UserService;
import lombok.CustomLog;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

//@CustomLog
@Slf4j(topic="UserController")
//@Log
@RestController
@RequestMapping(value = "/doctors")
public class UserController {

//	Map<String, UserRest> users;

	@Autowired
	UserService userRest;

	@Autowired
	private Environment environment;

	@GetMapping()
	public ResponseEntity<List<UserRest>> getUserWithParam(@RequestParam(value = "page", defaultValue = "1") int page,
	                                             @RequestParam(value = "limit", defaultValue = "1") int limit,
	                                             @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
//		if (true) throw new UserServiceException("test ");
		log.error("start <---------------");
		List<UserRest> userRestList = userRest.getAll();
		for (UserRest user: userRestList) {
			log.error(user.toString());
		}
		log.error(userRestList.toString());
		return new ResponseEntity<>(userRestList, HttpStatus.OK);
//		return "page = " + page + " limit = " + limit + " sort = " + sort;
	}


	@GetMapping(path = "/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {

		for (UserRest user: userRest.getAll()) {
			log.error(user.toString());
			log.error(user.getUserId() + " " + userId + " <-----------------------");
			log.error(userRest.getAll().stream().filter(u -> u.getUserId().equals(userId)).findFirst().get().getUserId());

		}
		userRest.getAll().stream().forEach(u -> log.error(u.toString()));
		log.error(userRest.getAll().stream().filter(u -> u.getUserId().equals(userId)).findFirst().get().toString() + " <-------- long my !!!! STREAM");

//		if (userRest.getAll()..containsKey(userId)) {
//			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
//		}
		log.error(environment.getProperty("local.server.port") + " <------------------------------- port ");
		return new ResponseEntity<>(userRest.getAll().stream().filter(u -> u.getUserId().equals(userId)).findFirst().get(), HttpStatus.OK);

	}


	@PostMapping(consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		return new ResponseEntity<>(userRest.createUser(userDetailsRequestModel), HttpStatus.OK);
	}


//	@PutMapping(path = "/{userId}", consumes = {
//			MediaType.APPLICATION_XML_VALUE,
//			MediaType.APPLICATION_JSON_VALUE},
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public UserRest updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetail) {
//		var storedUserDetail = users.get(userId);
//		storedUserDetail.setFirstName(userDetail.getFirstName());
//		storedUserDetail.setLastName(userDetail.getLastName());
//		users.put(userId, storedUserDetail);
//		return storedUserDetail;
//	}
//
//
//	@DeleteMapping(path = "/{userId}")
//	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
//		users.remove(userId);
//		return ResponseEntity.noContent().build();
//	}


	@GetMapping("/hateoas")
	public EntityModel<UserRest> getHateoasFormatTest() {
		//todo HATEOAS pattern
		UserRest doctorHATEOAS = UserRest.builder().firstName("Hateoas doctor").build();
		EntityModel<UserRest> resource = new EntityModel<>(doctorHATEOAS);
		WebMvcLinkBuilder linkToDoctor = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById("adf"));
		resource.add(linkToDoctor.withRel("First user named by Me"));
		return resource;
	}
}
