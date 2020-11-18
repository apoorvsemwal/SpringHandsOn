package com.microservicedemo.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservicedemo.models.CatalogItem;
import com.microservicedemo.models.Movie;
import com.microservicedemo.models.Rating;

@RestController
@RequestMapping("/catalog")   //Root URL - Redirect any URL starting with /catalog to this class
public class MovieCatalogResource {
	
	@RequestMapping("{/userId}") // equivalent to - /catalog/userId - /catalog comes from class level.
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		//Lets assume for the time being that the ratings API from ratings microservice returned this.
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3)
				);
		
		//For each rating item above we create a new catalog item. Also The name
		//is coming from movie-info-service.
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}
}
