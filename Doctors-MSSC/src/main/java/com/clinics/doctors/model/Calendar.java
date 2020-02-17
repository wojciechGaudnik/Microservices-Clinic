package com.clinics.doctors.model;

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

//	https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(
			cascade = CascadeType.ALL
			)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
}
