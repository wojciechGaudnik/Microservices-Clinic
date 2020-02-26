package com.clinics.doctors.ui.repositorie;

import com.clinics.doctors.ui.model.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long> {
}
