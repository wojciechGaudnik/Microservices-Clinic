package com.clinics.doctors.model;

import com.clinics.doctors.exception.validator.UniqueDoctorUUIDConstraint;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@ToString
//@Builder(toBuilder = true, builderMethodName = "hiddenBuilder")
@DynamicInsert
@DynamicUpdate
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "calendars"})
//@JsonIgnoreProperties({"calendars"})
//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator.class,
//		property = "id")
@UniqueDoctorUUIDConstraint
@Entity(name = "doctor")
public class Doctor {

//	public static DoctorBuilder builder(UUID uuid) {
//		return hiddenBuilder().uuid(uuid);
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

//	@JsonView(Views.Public.class)
	@Column(
			updatable = false,
			nullable = false,
			unique = true)
	private UUID doctoruuid; //todo bad name because JPA <---> sqlQuery

//	@JsonView(Views.Public.class)
	@NotBlank(message = "fistName is mandatory")
	@Size(min = 2, max = 100, message = "firstName length out of range")
	private String firstName;

//	@JsonView(Views.Public.class)
	@NotBlank(message = "lastName is mandatory")
	@Size(min = 3, max = 100, message = "lastName length out of range")
	private String lastName;

//	@JsonView(Views.Public.class)
	@URL
	@Column(unique = true)
	@Size(min = 3, max = 500, message = "photoUrl length out of range ")
	private String photoUrl;

//	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private String licence;


// https://springframework.guru/hibernate-show-sql/
//	https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//	@OneToMany(
//			mappedBy = "post",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//	)
//	@OneToMany(
//			mappedBy = "doctor",
////			fetch = FetchType.LAZY
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//			fetch = FetchType.EAGER  //todo <--- to s.... epsułem ?! Powinno być na odwrut ?
//			todo tutaj cascade all bo po drugiej stronie jest encja a nie lista więc powinien tam zapisać ?!
//			todo tam po stronie Calendars fetch eager bo wyciągnie i tak tylko 1 ID 1 doktora ?!
//	)
//	final private Collection<Calendar> calendars = new HashSet<>();
//	@JsonIgnore
//	@JsonBackReference
//	@JsonView(Views.Internal.class)
	@OneToMany(
			targetEntity=Calendar.class,
			mappedBy="doctor",
			cascade={CascadeType.ALL}, fetch = FetchType.LAZY,
			orphanRemoval=true)
	Collection<Calendar> calendars = new HashSet<>();

	@Builder.Default
	@JsonIdentityReference
//	@Column(nullable = false)
//	@ManyToMany (
//			targetEntity = Specialization.class,
////			fetch = FetchType.EAGER)
//			cascade = CascadeType.PERSIST)
//	@JoinTable(
//			name = "doctor_specialization",
//			joinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
//			inverseJoinColumns = {@JoinColumn(name = "specialization_id", referencedColumnName = "id")})
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	@ManyToMany(targetEntity = Specialization.class)
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = {@JoinColumn(name = "doctor_id")},
			inverseJoinColumns = {@JoinColumn(name = "spacialization_id")})
	Collection<Specialization> specializations = new HashSet<>();

	@JsonIgnore
	@ElementCollection
	final private Collection<UUID> patients = new HashSet<>();

	@ElementCollection
	final private Collection<UUID> medicalUnits = new HashSet<>();
}
