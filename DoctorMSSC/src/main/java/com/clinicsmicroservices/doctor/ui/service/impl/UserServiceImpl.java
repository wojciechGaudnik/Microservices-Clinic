package com.clinicsmicroservices.doctor.ui.service.impl;

import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;
import com.clinicsmicroservices.doctor.ui.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {
		UserRest userRest = UserRest.builder()
				.firstName(userDetailsRequestModel.getFirstName())
				.lastName(userDetailsRequestModel.getLastName())
				.email(userDetailsRequestModel.getEmail())
				.build();
		String userId = UUID.randomUUID().toString();
		userRest.setUserId(userId);
		if(users == null) users = new HashMap<>();
		users.put(userId, userRest);
		return userRest;
	}


}
