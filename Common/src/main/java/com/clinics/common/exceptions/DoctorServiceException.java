package com.clinics.common.exceptions;


public class DoctorServiceException extends RuntimeException{

	private static final long serialVersionUID = 1541234123412341234L;

	public DoctorServiceException(String message){
		super(message);
	}
}
