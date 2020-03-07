package com.clinics.common.DTO.response.outer;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class UserResponseDTO {
	private String token; //todo token in body is less secure than in header ? maybe we should hide this field ?
	private UUID uuid;
	private String email;
	private String role;
}
