package com.clinics.doctors.services;

import com.clinics.doctors.models.Doctor;
import com.clinics.doctors.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public String getDoctorByUUID(String UUID) {
		doctorRepository.save(new Doctor().toBuilder().build());
		return "Doctor test " + UUID;
	}
}
