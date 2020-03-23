package com.clinics.medicalunits.data.repositorie;

import com.clinics.medicalunits.data.model.MedicalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalUnitRepository extends JpaRepository<MedicalUnit, Long> {

	@Query("SELECT m FROM medical_unit m")
	List<MedicalUnit> getAll();
	Optional<MedicalUnit> findByMedicalUnitUUID(UUID medicalUnitUUID);
//	Optional<MedicalUnit> findByDoctorsUUID(UUID doctorsUUID);
}
