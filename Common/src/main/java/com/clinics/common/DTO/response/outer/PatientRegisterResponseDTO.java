package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class PatientRegisterResponseDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String pesel;

    private Collection<Object> visits;
}
