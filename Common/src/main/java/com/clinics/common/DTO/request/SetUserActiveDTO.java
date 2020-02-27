package com.clinics.common.DTO.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SetUserActiveDTO {
    private UUID uuid;
    private String role;
}
