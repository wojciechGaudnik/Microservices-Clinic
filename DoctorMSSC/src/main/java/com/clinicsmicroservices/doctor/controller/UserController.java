package com.clinicsmicroservices.doctor.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@GetMapping()
	public String getUser(){
		return "get user was called userId";
	}

	@GetMapping(path = "/{userId}")
	public String getUserById(@PathVariable String userId){
		return "get user was called userId = " + userId;
	}

	@PostMapping
	public String createUser(){
		return "create user was called";
	}

	@PutMapping
	public String updateUser(){
		return "update user was called";
	}

	@DeleteMapping
	public String deleteUser(){
		return "delete user was called";
	}
}
