package com.clinics.auth.configuration;

import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AsyncUserRepositoryAccess implements Runnable{


	private final UserRepository userRepository;

	public AsyncUserRepositoryAccess(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


//	@SneakyThrows
//	@Async
//	public void deleteNotEnabledUser(){
//		Thread.sleep(3000);
//		Optional<User> user = userRepository.findById(id);
//		if (user.isPresent() && !user.get().isEnable()) {
//			userRepository.delete(user.get());
//		}
//	}

	@Async
	@SneakyThrows
	@Override
	public void run() {
		log.warn("before 3 second");
		Thread.sleep(3000);
		log.warn("after 3 second");
		List<User> users = userRepository.findAllByisEnable(false);
		userRepository.deleteAll(users);
	}
}
