package com.clinics.common.DTO.request.outer;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Data
@ToString
public class RegisterMedicalUnitDTO {

	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

	@Size(min = 3, max = 100, message = "address length out of range")
	private String address;

	private Collection<UUID> doctors;  //todo remove !!!
}
