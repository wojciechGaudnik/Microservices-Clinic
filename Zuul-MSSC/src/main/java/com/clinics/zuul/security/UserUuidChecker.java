package com.clinics.zuul.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserUuidChecker {
	public boolean checkUserUUID(Authentication authentication, String uuid) {
		return uuid.equals(authentication.getCredentials().toString());
	}
}
