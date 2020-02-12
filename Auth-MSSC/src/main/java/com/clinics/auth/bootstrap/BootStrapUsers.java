package com.clinics.auth.bootstrap;

import com.clinics.auth.models.ClinicUser;
import com.clinics.auth.repositories.UserRepository;
import com.clinics.common.ConsoleColors;
import com.clinics.common.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BootStrapUsers implements CommandLineRunner, Role {

	@Autowired
	UserRepository userRepository;

	@Lazy
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		ClinicUser clinicUserPatient1 = ClinicUser
				.builder()
				.email("jan@jan.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.firstName("Jan")
				.lastName("Janjanowski")
				.photoUrl("http://jan.pl")
				.build();
		ClinicUser clinicUserPatient2 = ClinicUser
				.builder()
				.email("adam@adam.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.firstName("Adam")
				.lastName("Adamowski")
				.photoUrl("http://adam.pl")
				.build();
		ClinicUser clinicUserDoctor1 = ClinicUser
				.builder()
				.email("ola@ola.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.firstName("Ola")
				.lastName("Olkowska")
				.photoUrl("http://ola.pl")
				.build();
		ClinicUser clinicUserDoctor2 = ClinicUser
				.builder()
				.email("ala@ala.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.firstName("Ala")
				.lastName("Alowska")
				.photoUrl("http://ala.pl")
				.build();
		ClinicUser clinicUserAssistant1 = ClinicUser
				.builder()
				.email("zeta@zeta.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.firstName("Zeta")
				.lastName("Zetowska")
				.photoUrl("http://zeta.pl")
				.build();
		ClinicUser clinicUserAssistant2 = ClinicUser
				.builder()
				.email("anna@anna.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.firstName("Anna")
				.lastName("Annowska")
				.photoUrl("http://anna.pl")
				.build();
		ClinicUser creepy = ClinicUser
				.builder()
				.email("creepy@creepy.pl")
				.password(passwordEncoder.encode("666"))
				.role(Role.SYSTEM_ADMIN)
				.firstName("Creepy")
				.lastName("Creepowski")
				.photoUrl("http://creepy.pl")
				.build();

		List<ClinicUser> clinicUserList = Arrays.asList(
				clinicUserPatient1,
				clinicUserPatient2,
				clinicUserDoctor1,
				clinicUserDoctor2,
				clinicUserAssistant1,
				clinicUserAssistant2,
				creepy);

		userRepository.saveAll(clinicUserList);

		var user = userRepository.findById(1L).get();
		System.out.println(ConsoleColors.GREEN_BOLD);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(ConsoleColors.RESET);

	}
}



