package com.clinics.auth.exception;

import com.clinics.common.exceptions.CustomErrorMessage;
import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerAuth {
	@ExceptionHandler({ValidationException.class})
	public ResponseEntity<Object> handlerConstraintViolationException(Exception exception, WebRequest request) {
		CustomErrorMessage customErrorMessage = CustomErrorMessage
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Validation Exception")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handlerRuntimeExceptionException(Exception exception, WebRequest request) {
		CustomErrorMessage customErrorMessage = CustomErrorMessage
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("RuntimeException")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
