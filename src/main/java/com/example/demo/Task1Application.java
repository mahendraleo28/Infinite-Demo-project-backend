package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Task1Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Task1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
		System.out.println("hi this main ");
	}

	@GetMapping("/")
	public String welcome() {
		LOGGER.error("Message logged at ERROR level");
		LOGGER.warn("Message logged at WARN level");
		LOGGER.info("Message logged at INFO level");
		LOGGER.debug("Message logged at DEBUG level");
		return "Hello World!!";
	}
}
