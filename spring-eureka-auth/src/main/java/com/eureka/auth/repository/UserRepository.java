package com.eureka.auth.repository;

import com.eureka.auth.model.MedicalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MedicalUser, Long> {
    Optional<MedicalUser> findByUsername(String username);
}
