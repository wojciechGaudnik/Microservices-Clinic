package com.clinics.zuul.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserUUIDChecker {
	public boolean checkUserUUID(Authentication authentication, String uuid) {
		log.warn(authentication.getCredentials().toString() + " <--- from userUUIDChecker");
		log.warn(uuid + " <--- from userUUIDChecker");
		if (uuid.equals(authentication.getCredentials().toString())) {
			log.warn("The same <--- my");
		} else
			log.warn("Not the  same <--- my");
		return uuid.equals(authentication.getCredentials().toString());
	}
}
