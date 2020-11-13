package com.springdemo.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	//SpringBootServletInitializer initializes the Dispatcher Servlet. We need run this as Web App in Tomcat
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
