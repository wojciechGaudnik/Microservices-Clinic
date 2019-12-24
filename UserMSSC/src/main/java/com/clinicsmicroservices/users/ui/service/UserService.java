package com.clinicsmicroservices.users.ui.service;

import com.clinicsmicroservices.users.shared.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDetails);
}
