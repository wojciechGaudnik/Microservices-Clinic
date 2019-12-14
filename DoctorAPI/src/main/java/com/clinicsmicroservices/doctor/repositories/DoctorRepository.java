package com.clinicsmicroservices.doctor.repositories;

import com.clinicsmicroservices.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RepositoryRestController
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Override
	Optional<Doctor> findById(Long id);
	Doctor findFirstByFirstName(String name);
}
