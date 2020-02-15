package com.clinics.auth.bootstrap;

import com.clinics.auth.models.UserAuth;
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
public class BootStrapUser implements CommandLineRunner, Role {

	@Autowired
	UserRepository userRepository;

	@Lazy
	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		UserAuth userAuthPatient1 = UserAuth
				.builder()
				.email("jan@jan.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		UserAuth userAuthPatient2 = UserAuth
				.builder()
				.email("adam@adam.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		UserAuth userAuthDoctor1 = UserAuth
				.builder()
				.email("ola@ola.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		UserAuth userAuthDoctor2 = UserAuth
				.builder()
				.email("ala@ala.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		UserAuth userAuthAssistant1 = UserAuth
				.builder()
				.email("zeta@zeta.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		UserAuth userAuthAssistant2 = UserAuth
				.builder()
				.email("anna@anna.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		UserAuth creepy = UserAuth
				.builder()
				.email("creepy@creepy.pl")
				.password(passwordEncoder.encode("666"))
				.role(Role.SYSTEM_ADMIN)
				.build();

		List<UserAuth> userAuthList = Arrays.asList(
				userAuthPatient1,
				userAuthPatient2,
				userAuthDoctor1,
				userAuthDoctor2,
				userAuthAssistant1,
				userAuthAssistant2,
				creepy);

		userRepository.saveAll(userAuthList);

		var user = userRepository.findById(1L).get();
		System.out.println(ConsoleColors.GREEN_BOLD);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(ConsoleColors.RESET);

	}
}



