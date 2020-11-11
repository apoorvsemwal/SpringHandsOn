package com.testspringdata.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.testspringdata.entity.Flight;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {

	//We just declare it Spring Data would implement this for us
	List<Flight> findByOriginIgnoreCase(String origin);

	List<Flight> findByOriginIgnoreCaseAndDestinationIgnoreCase(String origin, String destination);

	List<Flight> findByOriginIn(String ... origins);
	
	Page<Flight> findByOriginIgnoreCase(String origin, Pageable pageable);
}
