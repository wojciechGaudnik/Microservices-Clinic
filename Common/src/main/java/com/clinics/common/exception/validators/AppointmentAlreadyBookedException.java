package com.clinics.common.exception.validators;

import java.util.UUID;

public class AppointmentAlreadyBookedException  extends RuntimeException{

    public AppointmentAlreadyBookedException(UUID uuid) {
        super(String.format("Appointment with uuid '%s' already booked", uuid));
    }
}

