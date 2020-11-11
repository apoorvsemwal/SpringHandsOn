package com.testspringdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.testspringdata.entity.Flight;
import com.testspringdata.repository.FlightRepository;

@DataJpaTest
public class PagingAndSortingTest {

	@Autowired
	private FlightRepository flightRepository;  //Setup automatically by JPA.

	//Each test should be independent
	@BeforeEach
	public void setUp() {
		flightRepository.deleteAll();
	}

	@Test
	public void shouldSortFlightsByOrigin() {
		final Flight flight1 = createFlight("Madraid");
		final Flight flight2 = createFlight("London");
		final Flight flight3 = createFlight("New York");
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		
		final Iterable<Flight> flights = flightRepository.findAll(Sort.by("origin"));
		assertThat(flights).hasSize(3);
		
		final Iterator<Flight> flightIterator = flights.iterator();
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight2);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight1);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight3);
	}

	@Test
	public void shouldSortFlightsByOriginAndThenBySchedule() {
		final Flight flight1 = createFlightWithSchedule("New York", LocalDateTime.parse("2020-11-12T12:12:00"));
		final Flight flight2 = createFlightWithSchedule("Argentina", LocalDateTime.parse("2021-11-12T12:12:00"));
		final Flight flight3 = createFlightWithSchedule("New York", LocalDateTime.parse("2020-11-12T12:10:00"));
		final Flight flight4 = createFlightWithSchedule("London", LocalDateTime.now().plusHours(1));
		final Flight flight5 = createFlightWithSchedule("London", LocalDateTime.now().minusHours(2));
		
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		flightRepository.save(flight4);
		flightRepository.save(flight5);
		
		final Iterable<Flight> flights = flightRepository.findAll(Sort.by("origin", "scheduledAt"));
		assertThat(flights).hasSize(5);
		
		final Iterator<Flight> flightIterator = flights.iterator();
		
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight2);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight5);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight4);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight3);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight1);
	}
	
	@Test
	public void shouldPageResults() {
		final Flight flight1 = createFlightWithSchedule("New York", LocalDateTime.parse("2020-11-12T12:12:00"));
		final Flight flight2 = createFlightWithSchedule("Argentina", LocalDateTime.parse("2021-11-12T12:12:00"));
		final Flight flight3 = createFlightWithSchedule("New York", LocalDateTime.parse("2020-11-12T12:10:00"));
		final Flight flight4 = createFlightWithSchedule("London", LocalDateTime.now().plusHours(1));
		final Flight flight5 = createFlightWithSchedule("London", LocalDateTime.now().minusHours(2));
		
		flightRepository.save(flight1);
		flightRepository.save(flight2);
		flightRepository.save(flight3);
		flightRepository.save(flight4);
		flightRepository.save(flight5);
		
		final Iterable<Flight> flights = flightRepository.findAll(Sort.by("origin", "scheduledAt"));
		assertThat(flights).hasSize(5);
		
		final Iterator<Flight> flightIterator = flights.iterator();
		
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight2);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight5);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight4);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight3);
		assertThat(flightIterator.next()).isEqualToComparingFieldByField(flight1);
	}	
	
	@Test
	public void shouldPageAndSortResults() {
		for (int i = 0; i < 50; i++) {
			final Flight flight = createFlight(String.valueOf(i));
			flightRepository.save(flight);
		}
		
		//Its like we want page number 2. Each Page here is defined to have a size of 5.
		//Page numbering starts from 0. So page zero has flights from 0 to 4 - Origin
		//Here since we have applied a descending order sort so page 0 will have from 45 - 49
		//Same way page two will have from 40 to 44 - Origin.
		final Page<Flight> page = flightRepository
				.findAll(PageRequest.of(2, 5, Sort.by(Direction.DESC, "origin")));
		
		assertThat(page.getTotalElements()).isEqualTo(50);
		assertThat(page.getNumberOfElements()).isEqualTo(5);
		assertThat(page.getTotalPages()).isEqualTo(10);
		assertThat(page.getContent())
			.extracting(Flight::getOrigin)
			.containsExactly("44","43","42","41","40");		
	}	

	@Test
	public void shouldPageAndSortADerivedQuery() {
		for (int i = 0; i < 10; i++) {
			final Flight flight = createFlight("London");
			flight.setDestination(String.valueOf(i));
			flightRepository.save(flight);
		}
		for (int i = 0; i < 10; i++) {
			final Flight flight = createFlight("Paris");
			flight.setDestination(String.valueOf(i));
			flightRepository.save(flight);
		}		
		//Its like we want page number 0. Each Page here is defined to have a size of 5.
		//Page numbering starts from 0. So page zero has flights from 0 to 4 - Destination - Source - London
		//Total - only 10 records with London so Total elements in Page are 10.
		//Here since we have applied a descending order sort so page 0 will have from 9 - 5 - Destination
		final Page<Flight> page = flightRepository
				.findByOriginIgnoreCase("London", (PageRequest.of(0, 5, Sort.by(Direction.DESC, "destination"))));
		
		assertThat(page.getTotalElements()).isEqualTo(10);
		assertThat(page.getNumberOfElements()).isEqualTo(5);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getContent())
			.extracting(Flight::getDestination)
			.containsExactly("9","8","7","6","5");		
	}
	
	private Flight createFlight(String origin) {
		final Flight flight = new Flight();
		flight.setOrigin(origin);
		flight.setDestination("Bengaluru");
		flight.setScheduledAt(LocalDateTime.parse("2020-11-12T12:12:00"));
		return flight;
	}
	
	private Flight createFlightWithSchedule(String origin, LocalDateTime schedule) {
		final Flight flight = new Flight();
		flight.setOrigin(origin);
		flight.setDestination("Bengaluru");
		flight.setScheduledAt(schedule);
		return flight;
	}	
}
