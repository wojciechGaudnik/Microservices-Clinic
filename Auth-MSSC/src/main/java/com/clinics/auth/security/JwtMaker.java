package com.clinics.auth.security;

import com.clinics.auth.models.UserAuth;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;


public interface JwtMaker extends JwtProperties {

	default String makeJwtToken(UserAuth userAuth) {
		return Jwts.builder()
				.setSubject(userAuth.getEmail())
				.claim("authorities", Collections.singletonList("ROLE_" + userAuth.getRole()))
				.claim("UUID", userAuth.getUuid())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
}
