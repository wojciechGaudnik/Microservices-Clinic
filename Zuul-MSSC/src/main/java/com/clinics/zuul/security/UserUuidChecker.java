package com.clinics.zuul.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Slf4j
@Component
public class UserUuidChecker {
	public boolean checkUserUUID(Authentication authentication, String uuid) {
		HashMap<String, Object> test = (HashMap<String, Object>)authentication.getCredentials();
		log.warn(String.valueOf(test.getClass() + " <------------------"));
		log.warn(String.valueOf(( test.get("UUID")) + " <------------------"));
		log.warn(String.valueOf(( test.get("isEnable")) + " <------------------"));
		return uuid.equals(((HashMap<String, Object>) authentication.getCredentials()).get("UUID"));
	}
}
