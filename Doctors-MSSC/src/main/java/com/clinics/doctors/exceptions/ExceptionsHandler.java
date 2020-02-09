package com.clinics.doctors.exceptions;

import com.clinics.common.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@Slf4j(topic="---> ExceptionsHandler <---")
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(NullPointerException.class)
//	public ResponseEntity<Object> handleNullPointerException(
//			NullPointerException nullPointerException,
//			WebRequest request) {
//		String errorMessage = nullPointerException.getMessage();
//		log.error(ConsoleColors.RED + request + " <--------- request"+ ConsoleColors.RESET);
//		if(errorMessage == null) errorMessage = nullPointerException.getLocalizedMessage();
//		return new ResponseEntity<>(
//				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
//		);
//	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(
			NoSuchElementException noSuchElementException,
			WebRequest request) {
		String errorMessage = noSuchElementException.getMessage();
		log.error(ConsoleColors.RED + request + " <--------- request"+ ConsoleColors.RESET);
		if(errorMessage == null) errorMessage = noSuchElementException.getLocalizedMessage();
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handlerAnyException(Exception exception, WebRequest request) {
		CustomErrorMessage errorMessage = new CustomErrorMessage(
				new Date(),
				(exception.getLocalizedMessage() == null)
						? exception.toString()
						: exception.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {
			NullPointerException.class,
			DoctorServiceException.class})
	public ResponseEntity<Object> handlerNullPointerException(Exception exception, WebRequest request) {  //todo <--- Exception because two Exception pass into this method
		CustomErrorMessage errorMessage = new CustomErrorMessage(
				new Date(),
				(exception.getLocalizedMessage() == null)
						? exception.toString()
						: exception.getLocalizedMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
