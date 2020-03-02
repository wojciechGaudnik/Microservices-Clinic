package com.clinics.zuul.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
public class UserUUIDChecker {

	public boolean checkUserUUID(Authentication authentication, String uuid) {
		if(
				authentication == null ||
				authentication.getCredentials() == null ||
				authentication.getCredentials().toString().length() == 0)
			return false;
			HashMap<String, Object> credentials = (HashMap<String, Object>)authentication.getCredentials();
			log.warn(String.valueOf(uuid.equals(credentials.get("UUID"))));
			log.warn(uuid);
			return uuid.equals(credentials.get("UUID"));
	}
}
