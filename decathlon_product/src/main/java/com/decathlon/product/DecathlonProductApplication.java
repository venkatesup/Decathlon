package com.decathlon.product;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients("com.decathlon.product")
@EnableEurekaClient
public class DecathlonProductApplication {

	private static final Logger logger = Logger
			.getLogger(DecathlonProductApplication.class);

	public static void main(String[] args) {

		logger.log(Level.INFO, "Decathlon Product Application");
		SpringApplication.run(DecathlonProductApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
