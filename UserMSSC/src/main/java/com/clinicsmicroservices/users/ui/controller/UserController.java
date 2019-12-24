package com.clinicsmicroservices.users.ui.controller;

import com.clinicsmicroservices.users.shared.UserDTO;
import com.clinicsmicroservices.users.ui.model.CreateUserRequestModel;
import com.clinicsmicroservices.users.ui.model.CreateUserResponseModel;
import com.clinicsmicroservices.users.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working from User on port " + environment.getProperty("local.server.port");
	}

	@PostMapping()
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDTO userDTO = modelMapper.map(userDetails, UserDTO.class);
		UserDTO createdUser = userService.createUser(userDTO);

		CreateUserResponseModel responseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
	}

}
