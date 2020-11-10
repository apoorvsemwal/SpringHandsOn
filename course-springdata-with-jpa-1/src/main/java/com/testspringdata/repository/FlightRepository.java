package com.testspringdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.testspringdata.entity.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {

	//We just declare it Spring Data would implement this for us
	List<Flight> findByOriginIgnoreCase(String origin);

	List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCase(String origin, String destination);

	List<Flight> findByOriginIn(String ... origins);

}
