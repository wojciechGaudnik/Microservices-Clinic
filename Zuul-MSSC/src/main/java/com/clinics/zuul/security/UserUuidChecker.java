package com.clinics.zuul.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Slf4j
@Component
public class UserUuidChecker {
//	@SneakyThrows
	public boolean checkUserUUID(Authentication authentication, String uuid) {
		if(
				authentication == null ||
				authentication.getCredentials() == null ||
				authentication.getCredentials().toString().length() == 0)
			return false;

		log.warn(String.valueOf(authentication.getCredentials().getClass()));

			HashMap<String, Object> credentials;
			credentials = (HashMap<String, Object>)authentication.getCredentials();
			log.warn(String.valueOf(credentials.getClass() + " <------------------"));
			log.warn(String.valueOf(( credentials.get("UUID")) + " <------------------"));
			log.warn(String.valueOf(( credentials.get("isEnable")) + " <------------------"));
			return uuid.equals(credentials.get("UUID"));

	}

}
