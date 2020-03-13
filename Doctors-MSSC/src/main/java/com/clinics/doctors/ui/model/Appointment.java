package com.clinics.doctors.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@ToString
@DynamicInsert
@DynamicUpdate
@Entity(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(updatable = false,
			nullable = false,
			unique = true)
	private UUID appointmentUUID;

	private LocalDateTime dateTime;
	private Duration duration;

	private UUID patientUUID;
	private String patientFirstName;
	private String patientSecondName;

	@JsonIgnore
	@ManyToOne(
			targetEntity= Calendar.class,
			fetch = FetchType.LAZY)
	@JoinColumn(name="calendar_id")
	private Calendar calendar;
}
