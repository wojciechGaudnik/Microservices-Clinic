package com.clinics.patient.exception;

import com.clinics.common.exception.ErrorMessageCustom;
import com.clinics.common.exception.validators.AppointmentAlreadyBookedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerPatient {

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

    @ExceptionHandler({VisitNotFoundException.class})
    public ResponseEntity<Object> removalOfNotFoundVisitException(Exception exception, WebRequest request) {
        ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .error("Visit not found")
                .errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
                .webRequest(request)
                .build();
        return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({AppointmentAlreadyBookedException.class})
    public ResponseEntity<Object> appointmentAlreadyBookedException(Exception exception, WebRequest request) {
        ErrorMessageCustom errorMessageCustom = ErrorMessageCustom
                .builder()
                .status(HttpStatus.CONFLICT)
                .error("Appointment already booked")
                .errors(new HashMap<>(){{put("defaultMessage", exception.getMessage());}})
                .webRequest(request)
                .build();
        return new ResponseEntity<>(errorMessageCustom, new HttpHeaders(), HttpStatus.CONFLICT);
    }
}
