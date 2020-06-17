package com.clinics.auth.data.bootstrap;

import com.clinics.auth.data.model.User;
import com.clinics.auth.data.repository.UserRepository;
import com.clinics.common.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class BootStrapUser implements CommandLineRunner, Role {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public BootStrapUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		init();
	}

	public void init(){
		User userPatient1 = User
				.builder()
				.userUUID(UUID.randomUUID())
				.email("jan@jan.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		User userPatient2 = User
				.builder()
				.userUUID(UUID.randomUUID())
				.email("adam@adam.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.build();
		User userPatient3 = User
				.builder()
				.userUUID(UUID.fromString("11f0f891-b243-4547-803b-605f72b11b11"))
				.email("maciej@maciej.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.PATIENT)
				.isEnabled(true)
				.build();
		User userDoctor1 = User
				.builder()
				.userUUID(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.email("ola@ola.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.isEnabled(true)
				.build();
		User userDoctor2 = User
				.builder()
				.userUUID(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))
				.email("ala@ala.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.DOCTOR)
				.isEnabled(true)
				.build();
		User userAssistant1 = User
				.builder()
				.userUUID(UUID.randomUUID())
				.email("zeta@zeta.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		User userAssistant2 = User
				.builder()
				.userUUID(UUID.randomUUID())
				.email("anna@anna.pl")
				.password(passwordEncoder.encode("12345"))
				.role(Role.ASSISTANT)
				.build();
		User creepy = User
				.builder()
				.userUUID(UUID.randomUUID())
				.email("creepy@creepy.pl")
				.password(passwordEncoder.encode("666999"))
				.role(Role.SYSTEM_ADMIN)
				.isEnabled(true)
				.build();

		List<User> userList = Arrays.asList(
				userPatient1,
				userPatient2,
				userPatient3,
				userDoctor1,
				userDoctor2,
				userAssistant1,
				userAssistant2,
				creepy);

		userRepository.saveAll(userList);
	}
}



