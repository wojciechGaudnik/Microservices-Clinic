package com.clinics.patient.repository;

import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisitRepository  extends JpaRepository<Visit, Long> {
    Visit findByUuid(UUID uuid);
}
