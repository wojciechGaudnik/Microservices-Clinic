package com.clinicsmicroservices.patient.repositories;

import com.clinicsmicroservices.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//@Repository
@RepositoryRestController
public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Override
	Optional<Patient> findById(Long id);
}
