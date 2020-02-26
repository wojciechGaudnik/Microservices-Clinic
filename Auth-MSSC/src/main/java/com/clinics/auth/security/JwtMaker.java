package com.clinics.auth.security;

import com.clinics.auth.ui.model.User;
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
				.claim(TOKEN_CLAIM_UUID, user.getUuid())
				.claim(TOKEN_CLAIM_ISENABLE, user.isEnable())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
}
