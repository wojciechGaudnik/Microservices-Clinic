package com.clinics.patient.exception;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(UUID uuid) {
        super(String.format("Patient with uuid '%s' not found", uuid));
    }
}
