package com.clinicsmicroservices.patient.bootstrap;

import com.clinicsmicroservices.patient.model.Patient;
import com.clinicsmicroservices.patient.repositories.PatientRepository;
import com.clinicsmicroservices.patient.tools.ConsoleColors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BootStrapData implements CommandLineRunner {

	private PatientRepository patientRepository;

	public BootStrapData(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(ConsoleColors.YELLOW + "Bootstrap START-----------------------------------------------------------------------------------------------");
		Patient patientFirst = Patient
				.builder()
				.uuid(UUID.randomUUID())
				.firstName("First Name Alloha World")
				.build();
		patientRepository.save(patientFirst);
		System.out.println(patientRepository.count());
		System.out.println(patientRepository.findById(1L).get().getFirstName());;
		System.out.println("Bootstrap END -----------------------------------------------------------------------------------------------" + ConsoleColors.RESET);
	}
}
