package com.clinics.doctors.ui.repositorie;

import com.clinics.doctors.ui.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional<Doctor> findByDoctoruuid(UUID uuid);
	boolean existsByDoctoruuid(UUID uuid);
	void deleteByDoctoruuid(UUID uuid);
}
