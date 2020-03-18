package com.clinics.auth.security;

import com.clinics.auth.data.model.User;
import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collections;
import java.util.Date;


public interface JwtMaker extends JwtProperties {

	default String makeJwtToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim(TOKEN_CLAIM_AUTHORITIES, Collections.singletonList(TOKEN_PREFIX_ROLE + user.getRole()))
				.claim(TOKEN_CLAIM_UUID, user.getUserUUID())
				.claim(TOKEN_CLAIM_ISENABLE, user.isEnabled())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
}
