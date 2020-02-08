package com.clinics.auth.controllers;

import com.clinics.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{UUID}")
	public ResponseEntity<String> getUserByUUID(@PathVariable String UUID){
		return ResponseEntity.ok().body(userService.getUserByUUID(UUID));
	}
}
