package com.clinicsmicroservices.doctor.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.StringJoiner;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
//@JsonIgnoreProperties(value = {"uuid", "Id"})
@JsonFilter(value = "DoctorFilter") //todo <--- connect with controller /filltering
@Entity(name = "doctor")
public class Doctor {

	//todo run auto ??
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
//	@JsonIgnore
	private UUID uuid;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank(message = "firstName is mandatory")
	@Size(min = 3, max = 100, message = "length out of range ")
	private String firstName;

	@Override
	public String toString() {
		return new StringJoiner(", ", Doctor.class.getSimpleName() + "[", "]")
				.add("Id=" + Id)
				.add("uuid=" + uuid)
				.add("firstName='" + firstName + "'")
				.toString();
	}
}
