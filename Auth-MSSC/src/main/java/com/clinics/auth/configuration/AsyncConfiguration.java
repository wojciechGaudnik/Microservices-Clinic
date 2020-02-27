package com.clinics.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(7);
		executor.setMaxPoolSize(42);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("Clinics-Executor");
		executor.initialize();
		return executor;
	}

//	@Override
//	todo public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return new MyAsyncUncaughtExceptionHandler();
//	}

}
