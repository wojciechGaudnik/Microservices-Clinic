package com.clinics.doctors.repositorie;

import com.clinics.doctors.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional<Doctor> findByUuid(UUID uuid);
}
