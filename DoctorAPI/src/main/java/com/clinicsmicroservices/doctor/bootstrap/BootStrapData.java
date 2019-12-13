package com.clinicsmicroservices.doctor.bootstrap;

import com.clinicsmicroservices.doctor.model.Doctor;
import com.clinicsmicroservices.doctor.repositories.DoctorRepository;
import com.clinicsmicroservices.doctor.tools.ConsoleColors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BootStrapData implements CommandLineRunner {

	private DoctorRepository doctorRepository;

	public BootStrapData(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(ConsoleColors.YELLOW + "Bootstrap START-----------------------------------------------------------------------------------------------");
		Doctor patientFirst = Doctor
				.builder()
				.uuid(UUID.randomUUID())
				.firstName("First Name Alloha Doctor's World")
				.build();
		Doctor patientSecond = Doctor
				.builder()
				.uuid(UUID.randomUUID())
				.firstName("Second Name Alloha Doctor's World")
				.build();
		Doctor patientThird = Doctor
				.builder()
				.uuid(UUID.randomUUID())
				.firstName("Third Name Alloha Doctor's World")
				.build();
		doctorRepository.save(patientFirst);
		doctorRepository.save(patientSecond);
		doctorRepository.save(patientThird);
		System.out.println(doctorRepository.count());
		System.out.println(doctorRepository.findById(1L).get().getFirstName());;
		System.out.println("Bootstrap END -----------------------------------------------------------------------------------------------" + ConsoleColors.RESET);
	}
}
