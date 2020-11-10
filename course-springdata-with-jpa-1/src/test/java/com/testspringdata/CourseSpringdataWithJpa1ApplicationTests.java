package com.testspringdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.testspringdata.entity.Flight;
import com.testspringdata.repository.FlightRepository;

@DataJpaTest
class CourseSpringdataWithJpa1ApplicationTests {
	
	@Autowired
	private EntityManager entityManager;  //Setup automatically by JPA.

	@Autowired
	private FlightRepository flightRepository;  //Setup automatically by JPA.
	
	@Test
	void verifyFlightCanBeSaved() {
		final Flight flight = new Flight();
		flight.setOrigin("Delhi");
		flight.setDestination("Bengaluru");
		flight.setScheduledAt(LocalDateTime.parse("2020-11-12T12:12:00"));
		entityManager.persist(flight); //Stores flight in an H2 Database
		
		final TypedQuery<Flight> results = entityManager
				.createQuery("SELECT f FROM Flight f", Flight.class);
		
		final List<Flight> resultList = results.getResultList();
		
		assertThat(resultList)
		.hasSize(1)
		.first()
		.isEqualTo(flight);
	}
	
	//Same test as above but using Repository now
	@Test
	void shouldPerformCrudOperations() {
		final Flight flight = new Flight();
		flight.setOrigin("Delhi");
		flight.setDestination("Bengaluru");
		flight.setScheduledAt(LocalDateTime.parse("2020-11-12T12:12:00"));
		flightRepository.save(flight); //Stores flight in an H2 Database
				
		assertThat(flightRepository.findAll())
		.hasSize(1)
		.first()
		.isEqualTo(flight);
		
		flightRepository.deleteById(flight.getId());
		
		assertThat(flightRepository.count()).isZero();
	}	

}
