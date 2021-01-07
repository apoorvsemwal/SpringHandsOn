package com.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UserDepartmentDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDepartmentDiscoveryServerApplication.class, args);
	}

}
