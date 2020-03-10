package com.clinics.patient.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerPatient {

    //niedozwolony delete 409
    @ExceptionHandler({RemovalOfFinishedVisitException.class})
    public ResponseEntity<Object> removalOfFinishedVisitException(Exception exception, WebRequest request) {
        ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
                .builder()
                .status(HttpStatus.CONFLICT)
                .error("It's not allowed to remove finished visit")
                .errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
                .webRequest(request)
                .build();
        return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.CONFLICT);
    }
}
