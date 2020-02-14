package com.clinics.doctors.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

//	@Column(
//			updatable = false,
//			nullable = false)
	private UUID uuid;

	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@Column(unique = true)
	@Size(min = 3, max = 100, message = "length out of range ")
	private String photoUrl;

	@ElementCollection
	private List<UUID> medicalUnits = new ArrayList<>();

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
}
