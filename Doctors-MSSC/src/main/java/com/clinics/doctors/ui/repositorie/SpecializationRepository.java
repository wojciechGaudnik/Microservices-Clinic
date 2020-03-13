package com.clinics.doctors.ui.repositorie;

import com.clinics.doctors.ui.model.Doctor;
import com.clinics.doctors.ui.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	Optional<Specialization> findBySpecializationUUID(UUID specializationUUID);
	List<Specialization> findAllByDoctors(Doctor doctor);
}
