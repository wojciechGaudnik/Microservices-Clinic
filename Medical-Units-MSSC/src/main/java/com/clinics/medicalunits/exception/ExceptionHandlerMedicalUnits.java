package com.clinics.medicalunits.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerMedicalUnits {

	@ExceptionHandler({
			HttpMessageNotReadableException.class,
			DataIntegrityViolationException.class,
			ValidationException.class,
			HttpClientErrorException.class,
	})
	public ResponseEntity<Object> handlerConstraintViolationException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.error("Validation Exception")
				.errors(new HashMap<>(){{
					put("defaultMessage:", exception.getMessage());
					put("source:", "medical-units");
				}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> handleNoSuchElementExceptionException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.NOT_FOUND)
				.error("No Such Element Exception")
				.errors(new HashMap<>(){{
					put("defaultMessage:", exception.getMessage());
					put("source:", "medical-units");
				}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handlerRuntimeExceptionException(Exception exception, WebRequest request) {
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("Runtime Exception")
				.errors(new HashMap<>(){{
					put("defaultMessage:", exception.getMessage());
					put("source:", "medical-units");
				}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
