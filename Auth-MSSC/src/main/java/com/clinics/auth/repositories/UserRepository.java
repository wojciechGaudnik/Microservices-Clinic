package com.clinics.auth.repositories;

import com.clinics.auth.models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuth, Long> {
	UserAuth findByEmail(String email);
}
