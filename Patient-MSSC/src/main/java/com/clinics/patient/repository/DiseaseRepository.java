package com.clinics.patient.repository;

import com.clinics.patient.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    void deleteDiseaseByDiseaseUUID(UUID diseaseUUID);
    Optional<Disease> findByDiseaseUUID(UUID diseaseUUID);
}
