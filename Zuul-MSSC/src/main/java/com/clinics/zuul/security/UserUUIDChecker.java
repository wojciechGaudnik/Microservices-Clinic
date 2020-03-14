package com.clinics.zuul.security;

import com.clinics.common.security.JwtProperties;
import com.clinics.common.security.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Slf4j
@Component
public class UserUUIDChecker implements JwtProperties {

	public boolean checkUserUUID(Authentication authentication, String uuid) {
		if(
				authentication == null ||
				authentication.getCredentials() == null ||
				authentication.getCredentials().toString().length() == 0)
			return false;
		if (authentication
				.getAuthorities()
				.stream()
				.anyMatch(a -> a.getAuthority().replace("ROLE_", "").equals(Role.SYSTEM_ADMIN))) {
			return true;
		}
			HashMap<String, Object> credentials = (HashMap<String, Object>)authentication.getCredentials();
			return uuid.equals(credentials.get("UUID"));
	}
}
