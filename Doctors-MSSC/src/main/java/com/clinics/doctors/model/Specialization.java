package com.clinics.doctors.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Entity
public class Specialization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Builder.Default
	@ManyToMany(mappedBy = "specializations")
	final private Collection<Doctor> doctors = new ArrayList<>();
}
