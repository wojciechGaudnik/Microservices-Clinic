package com.clinics.common.exception;


public class DoctorCustomException extends RuntimeException{

	private static final long serialVersionUID = 1541234123412341234L;

	public DoctorCustomException(String message){
		super(message);
	}
}