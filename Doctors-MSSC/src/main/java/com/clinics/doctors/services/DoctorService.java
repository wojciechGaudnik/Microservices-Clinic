package com.clinics.doctors.services;

import com.clinics.doctors.exceptions.DoctorServiceException;
import com.clinics.doctors.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public String getDoctorByUUID(UUID UUID) {
		return doctorRepository.findByUuid(UUID).getLicence();
	}

	public String getDoctorByID(Long id) {
		if (doctorRepository.findById(id).isEmpty()) {
			throw new DoctorServiceException("Id not found");
		}
		return doctorRepository.findById(id).get().getLicence();
	}
}
