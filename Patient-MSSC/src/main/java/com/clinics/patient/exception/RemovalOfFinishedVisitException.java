package com.clinics.patient.exception;

import java.util.UUID;

public class RemovalOfFinishedVisitException extends RuntimeException{

    public RemovalOfFinishedVisitException(UUID uuid) {
        super(String.format("This visit with uuid '%s' has been finished", uuid));
    }
}
