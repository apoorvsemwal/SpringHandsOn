package com.springdemo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springdemo.models.CovidStatsByLocation;

@Service
public class CovidDataService {

	private List<CovidStatsByLocation> allCovidStats = new ArrayList<CovidStatsByLocation>();

	//We'll use HTTP client to make HTTP Calls.
	private static String dataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	@PostConstruct
	//After this service instance is constructed just call this method to fetch the data using
	//an HTTP request. So this method is called automatically by Spring when app starts.

	@Scheduled(cron = "* * 1 * * *") //Its like saying execute this method every one hour.
	//Format is like sec min hr day month year
	//To run a method on a regular basis. Otherwise our data becomes out-dated on the next day while
	//the app is running say on some cloud service provider.
	public void fetchCovidData() throws IOException, InterruptedException {

		//Since many users are going to access our app so by the time this method processes data
		//we dont want them to receive error/empty msgs so we fill up this list here and then copy
		//it over to the global list. By the time this local list is getting populated users can be
		//served from the global list. This however is not the best way to do it. We don't create
		//globals like that.
		List<CovidStatsByLocation> covidStats = new ArrayList<CovidStatsByLocation>();

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(dataUrl))
				.build();

		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

		//Reader type is taken as input for CSV Parser so we create a StringReader 
		//that takes as input a string.
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			CovidStatsByLocation stat = new CovidStatsByLocation();
			stat.setState(record.get("Province/State"));
			stat.setCountry(record.get("Country/Region"));
			int todaysCaseCount = Integer.parseInt(record.get(record.size()-1).trim());
			int previousDayCaseCount = Integer.parseInt(record.get(record.size()-2).trim());
			stat.setLatestCaseCount(todaysCaseCount);
			stat.setDiffFromPrevDay(todaysCaseCount - previousDayCaseCount);
			covidStats.add(stat);
		}
		this.allCovidStats = covidStats;
	}

	public List<CovidStatsByLocation> getAllCovidStats() {
		return this.allCovidStats;
	}
	
}
