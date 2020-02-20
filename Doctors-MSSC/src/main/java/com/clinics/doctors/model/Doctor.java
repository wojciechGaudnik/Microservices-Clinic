package com.clinics.doctors.model;

import com.clinics.doctors.exception.validator.UniqueUUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
//@Builder(toBuilder = true, builderMethodName = "hiddenBuilder")
@Entity(name = "doctor")
public class Doctor {

//	public static DoctorBuilder builder(UUID uuid) {
//		return hiddenBuilder().uuid(uuid);
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(
			updatable = false,
			nullable = false)
	@UniqueUUIDConstraint
	private UUID doctor_uuid;

	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

	@URL
	@Column(unique = true)
	@Size(min = 3, max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

	@Column(nullable = false)
	private String licence;


// https://springframework.guru/hibernate-show-sql/
//	https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//	@OneToMany(
//			mappedBy = "post",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//	)
	@OneToMany(
			mappedBy = "doctor",
			fetch = FetchType.EAGER  //todo <--- to s.... epsułem ?! Powinno być na odwrut ?
//			todo tutaj cascade all bo po drugiej stronie jest encja a nie lista więc powinien tam zapisać ?!
//			todo tam po stronie Calendars fetch eager bo wyciągnie i tak tylko 1 ID 1 doktora ?!
	)
	final private Collection<Calendar> calendars = new ArrayList<>();

//	@Column(nullable = false)
	@ManyToMany(
			targetEntity = Specialization.class)
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = {@JoinColumn(name = "doctor_uuid")},
			inverseJoinColumns = {@JoinColumn(name = "specialization_id")})
	private Collection<Specialization> specializations;

	@JsonIgnore
	@ElementCollection
	final private Collection<UUID> patients = new HashSet<>();

	@ElementCollection
	final private Collection<UUID> medicalUnits = new HashSet<>();
}
