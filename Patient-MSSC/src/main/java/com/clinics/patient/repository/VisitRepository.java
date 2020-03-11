package com.clinics.patient.repository;

import com.clinics.patient.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface VisitRepository  extends JpaRepository<Visit, Long> {
    Optional<Visit> findByVisitUUID(UUID uuid);

    @Modifying
    void deleteByVisitUUID(UUID uuid);
}
