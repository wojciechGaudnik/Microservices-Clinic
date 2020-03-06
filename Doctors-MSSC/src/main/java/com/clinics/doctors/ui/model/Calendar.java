package com.clinics.doctors.ui.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@ToString
@Entity
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(
			updatable = false,
			nullable = false,
			unique = true)
	@Builder.Default
	private UUID calendaruuid = UUID.randomUUID(); //todo --- bad name because JPA <---> sqlQuery --- remove and put autogenerate in postgres
	//todo move creation uuid into method !!!

	@NotBlank(message = "name is mandatory")
	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

	@JsonIgnore
	@ManyToOne(
			targetEntity=Doctor.class,
			fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
}
