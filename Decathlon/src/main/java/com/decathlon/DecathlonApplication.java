package com.decathlon;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients("com.decathlon")
// @EnableEurekaClient
public class DecathlonApplication {

	private static final Logger logger = Logger
			.getLogger(DecathlonApplication.class);

	public static void main(String[] args) {

		logger.log(Level.INFO, "main class");
		SpringApplication.run(DecathlonApplication.class, args);
	}

	// @Bean
	// public Sampler defaultSampler() {
	// return new AlwaysSampler();
	// }
}
