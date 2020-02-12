package com.clinics.auth.repositories;

import com.clinics.auth.models.ClinicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ClinicUser, Long> {
	ClinicUser findByEmail(String s);
}
