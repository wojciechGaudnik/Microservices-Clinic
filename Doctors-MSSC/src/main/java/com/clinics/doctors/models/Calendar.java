package com.clinics.doctors.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@Entity
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(
			cascade = CascadeType.ALL
			)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;


//	@Id
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(
//			name = "UUID",
//			strategy = "org.hibernate.id.UUIDGenerator"
//	)
//	@Column(updatable = false, nullable = false)
//	private UUID uuid;
//
//	@NotBlank(message = "name is mandatory")
//	@Size(min = 3, max = 100, message = "firstName length out of range")
//	private String name;
//
//	@Column(updatable = false)
//	private UUID uuid_Unit;
//
//	@ManyToOne(targetEntity = Doctor.class)
////	@JoinColumn(name = "doctor_UUID")
//	private Doctor doctor;
}
