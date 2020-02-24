package com.clinics.doctors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
public class Specialization {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
			updatable = false,
			nullable = false,
			unique = true)
	@Builder.Default
	private UUID specializationuuid = UUID.randomUUID(); //todo bad name because JPA <---> sqlQuery


	@Column(nullable = false)
	private String name;

//	@Builder.Default
//	@ManyToMany(
//			mappedBy = "specializations")
//			cascade = CascadeType.PERSIST)
//	@ManyToMany(targetEntity = Doctor.class)
//	@ManyToMany(mappedBy = "specializations"
	@ManyToMany(targetEntity = Doctor.class)
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = {@JoinColumn(name = "spacialization_id")},
			inverseJoinColumns = {@JoinColumn(name = "doctor_id")})
	Collection<Doctor> doctors;
}
