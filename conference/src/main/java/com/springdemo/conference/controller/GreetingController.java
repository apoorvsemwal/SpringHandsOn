package com.springdemo.conference.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting (Map<String, Object> model) { //Method name does not matter here
		
		//Setting attrb to be passed back to jsp. In greeting jsp we have coded this key message.
		//View(JSPs) will have access to this model.
		model.put("message", "Hello Apoorv");
		
		//Returning the name of the jsp page we want to render next.
		//Based on the config(Prefix and Suffix) we have defined in application.properties or
		//ViewResolver Bean in config class - Spring knows where to look for JSP pages.
		return "greeting";		
	}
}
