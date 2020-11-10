package com.testspringdata;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testspringdata.entity.Flight;
import com.testspringdata.repository.FlightRepository;

//This annotation results in so created application context that will not contain the 
//whole context needed for our Spring Boot application, but instead only a “slice” of it 
//containing the components needed to initialize any JPA-related components like our 
//Spring Data repository.
//Each test method runs in its own transaction, 
//which is rolled back after the method has executed.
@DataJpaTest
public class DerivedQueryTests {

	@Autowired
	private FlightRepository flightRepository;  //Setup automatically by JPA.
	
	//Each test should be independent
	@BeforeEach
	public void setup() {
		flightRepository.deleteAll();
	}
	
	@Test
	public void shouldFindFlightsFromLondon() {
		final Flight flight1 = createFlight("London");
		final Flight flight2 = createFlight("London");
		final Flight flight3 = createFlight("New York");
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		
		List<Flight> flightsToLondon = flightRepository.findByOriginIgnoreCase("LONDON");
		assertThat(flightsToLondon).hasSize(2);
		assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByField(flight1);
		assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByField(flight2);
	}
	
	@Test
	public void shouldFindFlightsFromLondonToBengaluru() {
		final Flight flight1 = createFlight("London");
		final Flight flight2 = createFlight("London");
		final Flight flight3 = createFlight("New York");
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		
		List<Flight> flightsToLondon = flightRepository.findByOriginIgnoreCaseAndDestinationIgnoreCase("London", "BENGAluru");
		assertThat(flightsToLondon).hasSize(2);
		assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByField(flight1);
		assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByField(flight2);
	}

	@Test
	public void shouldFindFlightsFromLondonOrNewYork() {
		final Flight flight1 = createFlight("London");
		final Flight flight2 = createFlight("London");
		final Flight flight3 = createFlight("New York");
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		
		List<Flight> flightsToLondon = flightRepository.findByOriginIn("London", "New York");
		assertThat(flightsToLondon).hasSize(3);
		assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByField(flight1);
		assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByField(flight2);
		assertThat(flightsToLondon.get(2)).isEqualToComparingFieldByField(flight3);
	}
	
	private Flight createFlight(String origin) {
		final Flight flight = new Flight();
		flight.setOrigin(origin);
		flight.setDestination("Bengaluru");
		flight.setScheduledAt(LocalDateTime.parse("2020-11-12T12:12:00"));
		return flight;
	}
}
