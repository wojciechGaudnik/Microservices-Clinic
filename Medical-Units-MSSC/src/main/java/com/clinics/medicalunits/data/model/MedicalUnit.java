package com.clinics.medicalunits.data.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@ToString
@DynamicInsert
@DynamicUpdate
@Entity(name = "medical_unit")
public class MedicalUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(updatable = false,
			nullable = false,
			unique = true)
	private UUID medicalUnitUUID;

	@Column(unique = true)
	@NotBlank(message = "name is mandatory")
	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

	@NotBlank(message = "adress is mandatory")
	@Size(min = 3, max = 100, message = "address length out of range")
	private String address;  //todo <--- this we need to do properly

	@JsonIgnore
	@ElementCollection
	private Collection<UUID> patientsUUID;

	@ElementCollection
	private Collection<UUID> doctorsUUID;
}

