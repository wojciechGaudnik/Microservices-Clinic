package com.clinics.auth.bootstrap;

import com.clinics.auth.models.AuthUser;
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
		AuthUser authUserPatient1 = AuthUser
				.builder()
				.email("jan@jan.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		AuthUser authUserPatient2 = AuthUser
				.builder()
				.email("adam@adam.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		AuthUser authUserDoctor1 = AuthUser
				.builder()
				.email("ola@ola.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		AuthUser authUserDoctor2 = AuthUser
				.builder()
				.email("ala@ala.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		AuthUser authUserAssistant1 = AuthUser
				.builder()
				.email("zeta@zeta.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		AuthUser authUserAssistant2 = AuthUser
				.builder()
				.email("anna@anna.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		AuthUser creepy = AuthUser
				.builder()
				.email("creepy@creepy.pl")
				.password(passwordEncoder.encode("666"))
				.role(Role.SYSTEM_ADMIN)
				.build();

		List<AuthUser> authUserList = Arrays.asList(
				authUserPatient1,
				authUserPatient2,
				authUserDoctor1,
				authUserDoctor2,
				authUserAssistant1,
				authUserAssistant2,
				creepy);

		userRepository.saveAll(authUserList);

		var user = userRepository.findById(1L).get();
		System.out.println(ConsoleColors.GREEN_BOLD);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(ConsoleColors.RESET);

	}
}



