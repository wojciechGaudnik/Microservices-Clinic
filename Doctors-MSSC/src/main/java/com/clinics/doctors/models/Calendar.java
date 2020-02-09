package com.clinics.doctors.models;

import lombok.*;

import javax.persistence.*;


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
}
