package com.clinics.doctors.repositories;

import com.clinics.doctors.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
//public interface DoctorRepository extends CrudRepository<Doctor, UUID> {

}
