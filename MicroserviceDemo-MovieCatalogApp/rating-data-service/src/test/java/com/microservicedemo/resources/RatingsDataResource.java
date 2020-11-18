package com.microservicedemo.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicedemo.models.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getMovieRatings(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
}
