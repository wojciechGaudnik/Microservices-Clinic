package com.clinics.doctors.ui.repositorie;

import com.clinics.doctors.ui.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	Optional<Specialization> findBySpecializationUUID(UUID specializationUUID);
}
