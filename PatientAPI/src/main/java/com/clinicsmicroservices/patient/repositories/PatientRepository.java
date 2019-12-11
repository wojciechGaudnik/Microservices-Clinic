package com.clinicsmicroservices.patient.repositories;

import com.clinicsmicroservices.patient.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	@Override
	Optional<Patient> findById(Long id);
}