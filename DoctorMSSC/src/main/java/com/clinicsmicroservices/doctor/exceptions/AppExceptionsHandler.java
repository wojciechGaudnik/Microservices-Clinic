package com.clinicsmicroservices.doctor.exceptions;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//todo https://projectlombok.org/features/log
@CommonsLog
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handlerAnyException(Exception ex, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), (ex.getLocalizedMessage() == null) ? ex.toString() : ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handlerNullPointerException(Exception ex, WebRequest request) {  //todo <--- Exception because two Exception pass into this method
		ErrorMessage errorMessage = new ErrorMessage(new Date(), (ex.getLocalizedMessage() == null) ? ex.toString() : ex.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(value = {UserServiceException.class})  //todo <--- moved to NullPointerException
//	public ResponseEntity<Object> handlerUserServiceException(UserServiceException ex, WebRequest request) {
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), (ex.getLocalizedMessage() == null)? ex.toString(): ex.getLocalizedMessage());
//		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}


//	@ExceptionHandler(value = {Exception.class})
//	public EntityModel<Object> handlerAnyException(Exception ex, WebRequest request) {
//		return new EntityModel<>(new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.OK));
//	}
}
