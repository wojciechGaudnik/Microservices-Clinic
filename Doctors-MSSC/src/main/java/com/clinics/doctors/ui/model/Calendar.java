package com.clinics.doctors.ui.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
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

	@Column(updatable = false,
			nullable = false,
			unique = true)
	private UUID calendarUUID;

	@NotBlank(message = "name is mandatory")
	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

	private UUID medicalUnitUUID;

	@JsonIgnore
	@ManyToOne(
			targetEntity=Doctor.class,
			fetch = FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	@OneToMany(
			targetEntity=Appointment.class,
			mappedBy="calendar",
			cascade={CascadeType.ALL},
			fetch = FetchType.LAZY,
			orphanRemoval=true)
	private Collection<Appointment> appointments;
}
