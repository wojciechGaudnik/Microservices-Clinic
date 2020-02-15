package com.clinics.patient.repository;

import com.clinics.patient.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository  extends JpaRepository<Visit, Long> {
}
