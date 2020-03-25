package com.clinics.patient.repository;

import com.clinics.patient.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
