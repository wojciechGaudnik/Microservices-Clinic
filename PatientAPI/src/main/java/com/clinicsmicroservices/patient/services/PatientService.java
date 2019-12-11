package com.clinicsmicroservices.patient.services;

import com.clinicsmicroservices.patient.model.Patient;
import com.clinicsmicroservices.patient.repositories.PatientRepository;
import com.clinicsmicroservices.patient.tools.ConsoleColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PatientService {

	private PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Optional<Patient> getPatientById(Long id){
		log.debug(ConsoleColors.YELLOW + "Message from Service" + ConsoleColors.RESET);
		return patientRepository.findById(1L);
	}
}
