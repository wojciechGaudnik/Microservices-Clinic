package com.clinics.auth.ui.controller;

import com.clinics.auth.ui.service.UserService;
import com.clinics.common.DTO.request.EditUserInnerDTO;
import com.clinics.common.DTO.request.RegisterUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping(value = "/auth")
public class UserController {

	final private  UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/users/uuid/")
	public ResponseEntity<UUID> getUserUUID(HttpServletRequest request) {
		return ResponseEntity.status(200).body(userService.getUUID(request));
	}

	@PostMapping(
			value = "/users",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO){
		return ResponseEntity.status(201).body(userService.saveUser(registerUserDTO));
	}

	@PatchMapping(path = "/users/{userUUID}")
	public ResponseEntity<UserResponseDTO> editUser(
			@PathVariable UUID userUUID,
			@Valid @RequestBody EditUserInnerDTO editUserInnerDTO) {
		return ResponseEntity.status(200).body(userService.editUser(editUserInnerDTO, userUUID));
	}

	@PutMapping(path = "/users/{userUUID}")
	public ResponseEntity<UserResponseDTO> setUserEnable(@PathVariable UUID userUUID) {
		return ResponseEntity.status(201).body(userService.setUserEnable(userUUID));
	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> getTest(){
		return ResponseEntity.ok().body("Hello world from AUTH");
	}
}
