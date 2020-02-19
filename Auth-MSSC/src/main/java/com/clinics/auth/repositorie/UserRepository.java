package com.clinics.auth.repositorie;

import com.clinics.auth.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {

	Optional<UserDAO> findByEmail(String email);
	Boolean existsUserDAOByEmail(String email);

}
