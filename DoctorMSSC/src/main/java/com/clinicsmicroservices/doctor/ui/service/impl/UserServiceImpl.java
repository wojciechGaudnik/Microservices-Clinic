package com.clinicsmicroservices.doctor.ui.service.impl;

import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;
import com.clinicsmicroservices.doctor.ui.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j(topic="UserServiceImpl")
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
		if (users == null) users = new HashMap<>();
		users.put(userId, userRest);
		return userRest;
	}

	@Override
	public List<UserRest> getAll() {
		log.error("From userSerimp ,---------");
		for (UserRest user: users.values()) {
			log.error(user.toString());
		}
		return new LinkedList<>(users.values());
	}

	@Override
	public UserRest getById(String id) {
		return users.values().stream().filter(u -> u.getUserId().equals(id)).findFirst().get();
	}

}
