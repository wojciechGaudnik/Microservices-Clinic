package com.clinics.doctors.services;

import com.clinics.common.exceptions.DoctorServiceException;
import com.clinics.doctors.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public String getDoctorByUUID(UUID UUID) {
		return "There is no such doctor";
//		return (doctorRepository.findByUuid(UUID).getLicence() == null)? "There is no such doctor" : doctorRepository.findByUuid(UUID).getLicence();
	}

	public String getDoctorByID(Long id) {
		if (doctorRepository.findById(id).isEmpty()) {
			throw new DoctorServiceException("Id not found");
		}
		return doctorRepository.findById(id).get().getLicence();
	}
}
