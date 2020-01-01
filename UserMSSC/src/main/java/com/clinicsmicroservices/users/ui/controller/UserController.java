package com.clinicsmicroservices.users.ui.controller;

import com.clinicsmicroservices.users.shared.UserDTO;
import com.clinicsmicroservices.users.ui.model.CreateUserRequestModel;
import com.clinicsmicroservices.users.ui.model.CreateUserResponseModel;
import com.clinicsmicroservices.users.ui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Profile("dev")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working from User on port " + environment.getProperty("local.server.port") + environment.getProperty("token.secret");
	}

	@GetMapping("/status/check/JWT")
	public String statusJWT() {
		return "Proper JWT on port " + environment.getProperty("local.server.port") + ", with token = " + environment.getProperty("token.secret");
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



//	@GetMapping("/status/check/JWT")
//	public String statusJWT(@AuthenticationPrincipal User user) {
//		if (user == null) {
//			log.error("user == null < --------------------------------");
//		}
//		log.error(user.toString());
//		return "Proper JWT on port " + environment.getProperty("local.server.port") + "\nUser: ";
//	}

}
