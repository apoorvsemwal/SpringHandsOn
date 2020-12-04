package com.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//Tells spring to create a proxy to look for and call any methods marked with @Scheduled.
public class CovidStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidStatsApplication.class, args);
	}

}
