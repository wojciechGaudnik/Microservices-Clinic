package com.clinics.auth.ui.repositorie;

import com.clinics.auth.ui.model.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableCaching
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Optional<User> findByUuid(UUID userUUID);
	List<User> findAllByisEnable(boolean enabled);

//	@Async
//	void deleteById(Long id);
//
//	@Async
//	Optional<User> findById(Long id);
}
