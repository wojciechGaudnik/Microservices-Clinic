package com.clinicsmicroservices.doctor.exceptions;


public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 1541234123412341234L;


	public UserServiceException(String message) {
		super(message);
	}
}
