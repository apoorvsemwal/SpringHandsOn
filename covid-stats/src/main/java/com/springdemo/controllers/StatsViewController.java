package com.springdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springdemo.models.CovidStatsByLocation;
import com.springdemo.services.CovidDataService;

@Controller
//We just want it to render HTML UI
//If we declare this as @RestController it means all of the menthods in this class 
//return REST responses.
public class StatsViewController {
	
	//Withing controller methods we can set the Model Object and then set it in the context 
	//which can then be used by our static HTML pages.
	
	@Autowired
	//Controller gains access to service which has the data in it.
	CovidDataService covidDataService;
	
	@GetMapping("/")
	//Calling the root URL
	public String getMainViewTemplateName(Model model) {
		
		List<CovidStatsByLocation> covidStats = covidDataService.getAllCovidStats();
		int totalNumberOfCases = covidStats.stream().mapToInt(stat -> stat.getLatestCaseCount()).sum();
		
		//Its like setting the key value pair.
		model.addAttribute("covidStats", covidStats);
		model.addAttribute("totalReportedCases", totalNumberOfCases);
		model.addAttribute("pageTitle", "Country/Province wise daily COVID Stats...");
		
		return "home"; //Name of the HTML file we have created in templates folder
		//Thmyeleaf receives it and looks up this html file for us and renders it.
	}
}
