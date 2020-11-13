package com.springdemo.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springdemo.conference.model.Registration;

@Controller
public class RegistrationController {
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration (@ModelAttribute("registration")Registration registration) {
		//@ModelAttrb is bound at the JSP Page
		return "/WEB-INF/jsp/registration.jsp";		
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addRegistration (@ModelAttribute("registration")Registration registration) {
		System.out.println("Registration:"+registration.getName());
		return "/WEB-INF/jsp/registration.jsp";		
	}	
}
