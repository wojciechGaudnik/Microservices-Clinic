package com.clinics.auth.configuration;

import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AsyncUserRepositoryAccess implements Runnable{


	private final UserRepository userRepository;
	private final Long userId;

	public AsyncUserRepositoryAccess(UserRepository userRepository, Long userId) {
		this.userRepository = userRepository;
		this.userId = userId;
	}

	@Autowired
	public AsyncUserRepositoryAccess(UserRepository userRepository) {
		this.userRepository = userRepository;
		userId = 1L;  //todo bad !!!
	}


	@SneakyThrows  //todo simpler than try catch around sleep
	@Async
	@Override
	public void run() {
		Thread.sleep(60* 60 * 1000);  //todo move to environmental variable
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent() && !user.get().isEnable()) {
			userRepository.deleteById(userId);
		}
	}
}
