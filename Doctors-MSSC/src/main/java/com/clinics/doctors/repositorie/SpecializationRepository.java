package com.clinics.doctors.repositorie;

import com.clinics.doctors.model.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long> {
}
