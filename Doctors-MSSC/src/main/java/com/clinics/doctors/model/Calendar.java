package com.clinics.doctors.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@ToString
//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator.class,
//		property = "id")
@Entity
public class Calendar {

	@Id
	@JsonIgnore //todo add uuid out side
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@JsonView(Views.Public.class)
	@Column(
			updatable = false,
			nullable = false,
			unique = true)
	@Builder.Default
	private UUID calendaruuid = UUID.randomUUID(); //todo --- bad name because JPA <---> sqlQuery --- remove and put autogenerate in postgres

//	@JsonView(Views.Public.class)
	private String name;

//	https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//	@ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne(
//			fetch = FetchType.LAZY
//			)
//	@JoinColumn(name = "doctor_id")
//	@JsonManagedReference
//	@JsonView(Views.Public.class)
	@JsonIgnore
	@ManyToOne(targetEntity=Doctor.class, fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
}
