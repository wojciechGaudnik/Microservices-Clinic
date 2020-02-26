package com.clinics.auth.configuration;

import com.clinics.auth.ui.model.User;
import com.clinics.auth.ui.repositorie.UserRepository;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;


//@EnableScheduling
@Configuration
@EnableAsync
public class Schedule  implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		return new ThreadPoolTaskExecutor();
	}


	//	@Override
//	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//		scheduledTaskRegistrar.addTriggerTask();
//	}



//@Configuration
//@EnableAsync
//public class AppConfig implements AsyncConfigurer {
//
//	@Override
//	public Executor getAsyncExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(7);
//		executor.setMaxPoolSize(42);
//		executor.setQueueCapacity(11);
//		executor.setThreadNamePrefix("MyExecutor-");
//		executor.initialize();
//		return executor;
//	}
//
//	@Override
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return new MyAsyncUncaughtExceptionHandler();
//	}
//}
}
