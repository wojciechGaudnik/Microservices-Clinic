package com.clinicsmicroservices.doctor.repositories;

import com.clinicsmicroservices.doctor.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
	@Override
	Optional<Doctor> findById(Long id);
}
