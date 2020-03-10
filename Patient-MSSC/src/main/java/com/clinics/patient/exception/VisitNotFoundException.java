package com.clinics.patient.exception;

public class VisitNotFoundException extends RuntimeException{

    public VisitNotFoundException(String message) {
        super(message);
    }
}
