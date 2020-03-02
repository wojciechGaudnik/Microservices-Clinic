package com.clinics.common.DTO.request;

import lombok.Data;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class RegisterPatientDTO {

    @NotNull(message = "uuid cannot be null")
    private UUID uuid;

    @NotBlank(message = "fistName is mandatory")
    @Size(min = 2, max = 100, message = "firstName length out of range")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    @Size(min = 3, max = 100, message = "lastName length out of range")
    private String lastName;

    @PESEL
    @NotBlank(message = "pesel is mandatory")
    private String pesel;

    @Size(max = 500, message = "photoUrl length out of range ")
    private String photoUrl;
}
