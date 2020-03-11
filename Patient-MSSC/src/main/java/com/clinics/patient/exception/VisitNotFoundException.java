package com.clinics.patient.exception;

import java.util.UUID;

public class VisitNotFoundException extends RuntimeException{

    public VisitNotFoundException(UUID uuid) {
        super(String.format("Visit with uuid '%s' not found", uuid));
    }
}
