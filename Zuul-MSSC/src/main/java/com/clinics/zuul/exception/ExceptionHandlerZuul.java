package com.clinics.zuul.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import com.clinics.common.exception.ZuulCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerZuul {
	@ExceptionHandler({ClassCastException.class})
	public ResponseEntity<Object> handleClassCastException() {
		log.error("@ExceptionHandler({ClassCastException.class})");
//		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
//				.builder()
//				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.error("RuntimeException")
//				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
//				.webRequest(request)
//				.build();
//		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler({ZuulCustomException.class})
	public ResponseEntity<Object> handleZuulCustomException(Exception exception, WebRequest request) {
		log.error("@ExceptionHandler({ZuulCustomException.class})");
		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
				.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.error("RuntimeException")
				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
				.webRequest(request)
				.build();
		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException() {
		log.error("@ExceptionHandler(RuntimeException.class)");
//		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
//				.builder()
//				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.error("RuntimeException")
//				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
//				.webRequest(request)
//				.build();
//		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(Exception.class)
	public void handleException() {
		log.error("@ExceptionHandler(Exception.class) < -------------");
//		ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
//				.builder()
//				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.error("RuntimeException")
//				.errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
//				.webRequest(request)
//				.build();
//		return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//		return ResponseEntity.noContent().build();
	}

//	@ExceptionHandler
//	public String handleALLException(Exception exception) {
//		log.error("handleALLException <---my ");
//		return "Main exception";
//	}
}
