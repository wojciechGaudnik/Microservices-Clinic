package com.clinics.common.DTO.request;

import lombok.Data;

@Data
public class RegisterPatientDTO {
    private String pesel;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String token;
}
