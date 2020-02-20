package com.clinics.doctors.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerDoctor {

//	@ExceptionHandler({HttpMessageNotReadableException.class})
//	public ResponseEntity<Object> handlerConstraintViolationExceptionPlain() {
//		log.error("------------------------------------");
//		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
//				.builder()
//				.status(HttpStatus.UNPROCESSABLE_ENTITY)
//				.error("Validation Exception")
//				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
//				.webRequest(request)
//				.build();
//		return new ResponseEntity<>("errorMessageCustom", new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
//	}

	@ExceptionHandler({HttpMessageNotReadableException.class, DataIntegrityViolationException.class, ValidationException.class})
	public ResponseEntity<Object> handlerConstraintViolationException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Validation Exception")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handlerRuntimeExceptionException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("Runtime Exception")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
