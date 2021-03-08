package com.itevent.iteventapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IteventApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IteventApiApplication.class, args);
		System.out.println("main application running");
	}
}
