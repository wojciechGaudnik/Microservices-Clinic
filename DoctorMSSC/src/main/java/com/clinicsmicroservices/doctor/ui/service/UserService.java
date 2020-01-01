package com.clinicsmicroservices.doctor.ui.service;

import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;

import java.util.List;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
	List<UserRest> getAll();
	UserRest getById(String id);
}
