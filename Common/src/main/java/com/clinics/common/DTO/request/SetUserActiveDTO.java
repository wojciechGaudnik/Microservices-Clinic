package com.clinics.common.DTO.request;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class SetUserActiveDTO {
    private UUID uuid;
    private String role;
}
