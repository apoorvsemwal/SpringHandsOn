package com.testspringdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.testspringdata.entity.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {

}
