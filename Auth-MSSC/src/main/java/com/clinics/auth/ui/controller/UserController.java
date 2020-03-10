package com.clinics.auth.ui.controller;

import com.clinics.auth.ui.service.UserService;
import com.clinics.common.DTO.request.inner.EditUserDTO;
import com.clinics.common.DTO.request.outer.RegisterUserDTO;
import com.clinics.common.DTO.response.outer.UserResponseDTO;
import com.clinics.common.DTO.response.outer.UserUUIDAndROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@GetMapping(path = "/users/uuidAndRole/")
	public ResponseEntity<UserUUIDAndROLE> getUserUUIDAndROLE(HttpServletRequest request) {
		return ResponseEntity.ok().body(userService.getUUIDAndROLE(request));
	}

	@PostMapping(
			value = "/users",
			consumes = {
					MediaType.APPLICATION_JSON_VALUE},
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegisterUserDTO registerUserDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(registerUserDTO));
	}

	@PatchMapping(path = "/users/{userUUID}")
	public ResponseEntity<Void> editUser(
			@PathVariable UUID userUUID,
			@Valid @RequestBody EditUserDTO editUserDTO) {
		userService.edit(editUserDTO, userUUID);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/users/{userUUID}")
	public ResponseEntity<Void> setUserEnable(@PathVariable UUID userUUID) {
		userService.setUserEnable(userUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/users/{userUUID}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID userUUID) {
		userService.delete(userUUID);
		return ResponseEntity.ok().build();
	}



	@GetMapping(value = "/test/{text}")
	public ResponseEntity<String> getTest(@PathVariable String text) {
		return ResponseEntity.ok().body("Hello world from AUTH plus text : " + text);
	}
}
