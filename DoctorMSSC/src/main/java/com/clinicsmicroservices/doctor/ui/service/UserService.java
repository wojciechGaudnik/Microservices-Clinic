package com.clinicsmicroservices.doctor.ui.service;

import com.clinicsmicroservices.doctor.ui.model.request.UserDetailsRequestModel;
import com.clinicsmicroservices.doctor.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
