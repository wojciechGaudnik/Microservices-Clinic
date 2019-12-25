package com.clinicsmicroservices.users.ui.service;

import com.clinicsmicroservices.users.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	UserDTO createUser(UserDTO userDetails);
	UserDTO getUserDetailsByEmail(String email);
}
