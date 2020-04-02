package com.clinics.patient.exception;

import java.util.UUID;

public class DiseaseNotFoundException extends RuntimeException{

    public DiseaseNotFoundException(UUID uuid) {
        super(String.format("Disease with uuid '%s' not found", uuid));
    }
}
