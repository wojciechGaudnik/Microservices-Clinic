package com.clinics.auth.ui.repositorie;

import com.clinics.auth.ui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Optional<User> findByUserUUID(UUID userUUID);

	@Modifying
	void deleteByUserUUID(UUID userUUID);
}
