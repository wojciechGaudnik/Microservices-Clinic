package com.clinics.common.DTO.request.outer;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@ToString
public class RegisterPatientDTO {

    @NotNull(message = "uuid cannot be null")
    private UUID patientUUID;

    @NotBlank(message = "fistName is mandatory")
    @Size(min = 2, max = 100, message = "firstName length out of range")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    @Size(min = 3, max = 100, message = "lastName length out of range")
    private String lastName;

    @NotBlank(message = "pesel is mandatory")
    @Size(min = 11, max = 11, message = "Pesel must be exactly 11 digits long")
    private String pesel;

    @Size(max = 500, message = "photoUrl length out of range ")
    private String photoUrl;
}
