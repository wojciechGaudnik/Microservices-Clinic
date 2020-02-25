package com.clinics.zuul.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserUUIDChecker {

	public boolean checkUserUUID(Authentication authentication, String uuid) {
		if(
				authentication == null ||
				authentication.getCredentials() == null ||
				authentication.getCredentials().toString().length() == 0)
			return false;
			HashMap<String, Object> credentials = (HashMap<String, Object>)authentication.getCredentials();
			return uuid.equals(credentials.get("UUID"));
	}
}
