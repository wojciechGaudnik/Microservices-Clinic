package com.clinics.auth.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerAuth {
	@ExceptionHandler({ValidationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Validation Exception")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> handleNoSuchElementExceptionException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("No Such Element Exception")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("RuntimeException")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
