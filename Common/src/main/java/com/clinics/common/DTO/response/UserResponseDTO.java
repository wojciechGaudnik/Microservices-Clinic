package com.clinics.common.DTO.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class UserResponseDTO {
	private String token;
	private UUID uuid;
	private String email;
	private String role;
	private String firstName;
	private String lastName;
	private String photoUrl;
}
