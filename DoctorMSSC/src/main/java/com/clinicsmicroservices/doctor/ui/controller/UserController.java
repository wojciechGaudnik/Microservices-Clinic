package com.clinicsmicroservices.doctor.ui.controller;

import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@GetMapping
	public String getUserWithParam(@RequestParam(value = "page", defaultValue = "1") int page,
	                               @RequestParam(value = "limit", defaultValue = "1") int limit,
	                               @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "page = " + page + " limit = " + limit + " sort = " + sort;
	}

	@GetMapping(path = "/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {
		UserRest userRest = UserRest.builder()
				.firstName("Jan")
				.lastName("Kowalsi")
				.email("ble@ble.com")
				.userId(Integer.valueOf(userId))
				.build();
		return new ResponseEntity<>(userRest, HttpStatus.OK);
	}

	@PostMapping(consumes = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		UserRest userRest = UserRest.builder()
				.firstName(userDetailsRequestModel.getFirstName())
				.lastName(userDetailsRequestModel.getLastName())
				.email(userDetailsRequestModel.getEmail())
				.build();
		return new ResponseEntity<>(userRest, HttpStatus.OK);
	}

	@PutMapping
	public String updateUser() {
		return "update user was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
}
