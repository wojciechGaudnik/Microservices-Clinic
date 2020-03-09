package com.clinics.common.security;

public interface JwtProperties {
	String TOKEN_LOGIN_URI = "/auth/login";
	//CONFUSING NAME, CHANGE TO AUTHORIZATION OR STH
	String TOKEN_REQUEST_HEADER = "Authorization";
	String TOKEN_PREFIX = "Bearer ";
	String TOKEN_PREFIX_ROLE = "ROLE_";
	int TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
	byte[] TOKEN_SECRET = "ClinicsSecretKey".getBytes();
	String TOKEN_CLAIM_AUTHORITIES= "authorities";
	String TOKEN_CLAIM_UUID = "UUID";
	String TOKEN_CLAIM_ISENABLE = "isEnable";
}
