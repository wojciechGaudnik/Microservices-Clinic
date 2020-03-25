package com.clinics.patient.repository;

import com.clinics.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PatientRepository extends  JpaRepository<Patient, Long> {
	Optional<Patient> findByPatientUUID(UUID patientUUID);
	Optional<Patient> findById(Long id);
	void deleteByPatientUUID(UUID patientUUID);
	List<Patient> findAll();
}
