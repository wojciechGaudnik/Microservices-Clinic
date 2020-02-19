package com.clinics.auth.bootstrap;

import com.clinics.auth.model.UserDAO;
import com.clinics.auth.repositorie.UserRepository;
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

	@Lazy
	@Autowired
	private UserRepository userRepository;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public void run(String... args) throws Exception {
		init();
	}

	public void init(){
		UserDAO userDAOPatient1 = UserDAO
				.builder()
				.email("jan@jan.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		UserDAO userDAOPatient2 = UserDAO
				.builder()
				.email("adam@adam.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		UserDAO userDAODoctor1 = UserDAO
				.builder()
				.email("ola@ola.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		UserDAO userDAODoctor2 = UserDAO
				.builder()
				.email("ala@ala.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.build();
		UserDAO userDAOAssistant1 = UserDAO
				.builder()
				.email("zeta@zeta.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		UserDAO userDAOAssistant2 = UserDAO
				.builder()
				.email("anna@anna.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		UserDAO creepy = UserDAO
				.builder()
				.email("creepy@creepy.pl")
				.password(passwordEncoder.encode("666"))
				.role(Role.SYSTEM_ADMIN)
				.build();

		List<UserDAO> userDAOList = Arrays.asList(
				userDAOPatient1,
				userDAOPatient2,
				userDAODoctor1,
				userDAODoctor2,
				userDAOAssistant1,
				userDAOAssistant2,
				creepy);

		userRepository.saveAll(userDAOList);
		System.out.println(userRepository);

		var user = userRepository.findById(1L).get();
		System.out.println(ConsoleColors.GREEN_BOLD);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(ConsoleColors.RESET);
	}
}



