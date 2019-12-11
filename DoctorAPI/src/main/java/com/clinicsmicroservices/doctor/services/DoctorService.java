package com.clinicsmicroservices.doctor.services;

import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.repositories.DoctorRepository;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DoctorService {

	private DoctorRepository doctorRepository;

	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	public Optional<Doctor> getDoctorByID(Long id){
		log.debug(ConsoleColors.YELLOW + "Message from Service" + ConsoleColors.RESET);
		return doctorRepository.findById(1L);
	}
}
