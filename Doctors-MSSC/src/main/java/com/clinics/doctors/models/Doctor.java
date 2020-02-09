package com.clinics.doctors.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
			updatable = false,
			nullable = false)
	private UUID uuid;

	@Column(nullable = false)
	private String licence;

	@OneToMany(
			mappedBy = "doctor",
			fetch = FetchType.EAGER
	)
	private Collection<Calendar> calendars = new ArrayList<>();

	@Column(nullable = false)
	@ManyToMany(
			targetEntity = Specialization.class)
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = {@JoinColumn(name = "doctor_uuid")},
			inverseJoinColumns = {@JoinColumn(name = "specialization_id")})
	private Collection<Specialization> specializations;

//	todo private Collection<Patients> patients;

	//	@Id
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(
//			name = "UUID",
//			strategy = "org.hibernate.id.UUIDGenerator"
//	)
//	@Column(updatable = false, nullable = false)
//	private UUID uuid;
}
