package com.clinics.auth.security;

import com.clinics.auth.model.User;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;


public interface JwtMaker extends JwtProperties {

	default String makeJwtToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("authorities", Collections.singletonList("ROLE_" + user.getRole()))
				.claim("UUID", user.getUuid())
				.claim("isEnable", user.isEnable())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
}
