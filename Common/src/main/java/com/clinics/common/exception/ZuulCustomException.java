package com.clinics.common.exception;


public class ZuulCustomException  extends Exception{

	private static final long serialVersionUID = 151245123412341234L;

	public ZuulCustomException() {
		super();
	}

	public ZuulCustomException(String message) {
	}

	public ZuulCustomException(ClassCastException message) {
	}

	public ZuulCustomException(Exception exception) {
		super(exception);
	}
}
