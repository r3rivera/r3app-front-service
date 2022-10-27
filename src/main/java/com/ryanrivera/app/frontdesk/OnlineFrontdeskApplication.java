package com.ryanrivera.app.frontdesk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class OnlineFrontdeskApplication {

	public static void main(String[] args) {
		log.info("Starting the online store ...");
		SpringApplication.run(OnlineFrontdeskApplication.class, args);
	}

}
