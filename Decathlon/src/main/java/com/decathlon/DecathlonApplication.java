package com.decathlon;

import org.jboss.logging.*;
import org.jboss.logging.Logger.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableEurekaClient
public class DecathlonApplication {

	private static final Logger logger = Logger.getLogger(DecathlonApplication.class);

	public static void main(String[] args) {
		
		logger.log(Level.INFO, "main class");
		SpringApplication.run(DecathlonApplication.class, args);
	}
}
