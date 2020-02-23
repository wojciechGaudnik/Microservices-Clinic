package com.clinics.auth.controller;

import com.clinics.auth.service.UserService;
import com.clinics.common.DTO.request.RegisterUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/auth")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> getTest(){
		log.error("---> " );
		return ResponseEntity.ok().body("Hello world from AUTH");
	}

	@PostMapping(value = "/users")
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO){
		return ResponseEntity.status(201).body(userService.saveUser(registerUserDTO));
	}

	@PutMapping(path = "/users/{userUUID}")
	public ResponseEntity<UserResponseDTO> setUserEnable(@PathVariable UUID userUUID) {
		return ResponseEntity.status(201).body(userService.setUserEnable(userUUID));
	}
}
