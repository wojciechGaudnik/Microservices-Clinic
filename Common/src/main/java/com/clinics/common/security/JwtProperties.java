package com.clinics.common.security;

public interface JwtProperties {
	String TOKEN_LOGIN_URI = "/auth/login";
	String TOKEN_REQUEST_HEADER = "Authorization";
	String TOKEN_PREFIX = "Bearer ";
	int TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
	byte[] TOKEN_SECRET = "ClinicsSecretKey".getBytes();
}
