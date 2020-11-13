package com.springdemo.conference.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springdemo.conference.model.Registration;

@Controller
public class RegistrationController {
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration (@ModelAttribute("registration")Registration registration) {
		//@ModelAttrb is bound at the JSP Page
		return "registration";//ViewResolver will receive this string and find it.
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addRegistration (@Valid @ModelAttribute("registration")Registration registration,
			BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("There were errors.");
			return "registration";
		}
		System.out.println("Registration:"+registration.getName());
		//redirect for POST-Redirect-GET
		return "redirect:registration";
	}	
}
