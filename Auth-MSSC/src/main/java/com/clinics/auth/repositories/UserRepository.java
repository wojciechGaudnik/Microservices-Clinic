package com.clinics.auth.repositories;

import com.clinics.auth.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {
	AuthUser findByEmail(String email);
}
