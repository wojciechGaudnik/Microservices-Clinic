package com.clinics.auth.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public String getUserByUUID(String UUID){
		return "User service test" + UUID;
	}
}
