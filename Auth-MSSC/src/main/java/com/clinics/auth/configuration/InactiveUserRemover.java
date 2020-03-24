package com.clinics.auth.configuration;

import com.clinics.auth.data.model.User;
import com.clinics.auth.data.repository.UserRepository;

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
		if(user.isPresent() && !user.get().isEnabled()) {
			System.out.println("Removing user of id " + userId);
			userRepository.deleteById(userId);
		}
	}
}
