package com.assignment.user;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserAssignmentApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserAssignmentApplication.class, args);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}
	@Bean
	public MeterRegistry meterRegistry() {
		return new SimpleMeterRegistry();
	}

}
