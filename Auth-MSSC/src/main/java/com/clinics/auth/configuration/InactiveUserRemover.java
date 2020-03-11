package com.clinics.auth.configuration;

import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class InactiveUserRemover implements Runnable{

	private final UserRepository userRepository;
	private final Long userId;

	public InactiveUserRemover(UserRepository userRepository, Long userId) {
		this.userRepository = userRepository;
		this.userId = userId;
	}

	public void run() {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent() && !user.get().isEnable()) {
			System.out.println("Removing user of id " + userId);
			userRepository.deleteById(userId);
		}
	}
}
