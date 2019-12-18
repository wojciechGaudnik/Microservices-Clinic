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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@CustomLog
//@Slf4j(topic="UserController")
@Log
@RestController
@RequestMapping(value = "/doctors")
public class UserController {

	Map<String, UserRest> users;

	@Autowired
	UserService userRest;

	@GetMapping()
	public String getUserWithParam(@RequestParam(value = "page", defaultValue = "1") int page,
	                               @RequestParam(value = "limit", defaultValue = "1") int limit,
	                               @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		if(true) throw  new UserServiceException("test ");

		return "page = " + page + " limit = " + limit + " sort = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {
		log.info(userId + " my error");

		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
//		UserRest userRest = UserRest.builder()
//				.firstName(userDetailsRequestModel.getFirstName())
//				.lastName(userDetailsRequestModel.getLastName())
//				.email(userDetailsRequestModel.getEmail())
//				.build();
//		String userId = UUID.randomUUID().toString();
//		userRest.setUserId(userId);
//		if(users == null) users = new HashMap<>();
//		users.put(userId, userRest);


		return new ResponseEntity<>(userRest.createUser(userDetailsRequestModel), HttpStatus.OK);
	}

	@PutMapping(path="/{userId}", consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRest updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetail) {
		var storedUserDetail = users.get(userId);
		storedUserDetail.setFirstName(userDetail.getFirstName());
		storedUserDetail.setLastName(userDetail.getLastName());
		users.put(userId, storedUserDetail);
		return storedUserDetail;
	}

	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}

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
