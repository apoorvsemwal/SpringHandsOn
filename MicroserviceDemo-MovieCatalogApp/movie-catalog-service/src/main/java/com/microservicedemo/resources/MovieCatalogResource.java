package com.microservicedemo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.microservicedemo.models.CatalogItem;
import com.microservicedemo.models.Movie;
import com.microservicedemo.models.UserRating;

@RestController
@RequestMapping("/catalog")   //Root URL - Redirect any URL starting with /catalog to this class
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	//	@Autowired
	//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}") // equivalent to - /catalog/userId - /catalog comes from class level.
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		//No hardcoding now we will be using service discovery - client side.
		//UserRating usersRatings = restTemplate.getForObject("http://localhost:8083/users/"+userId, UserRating.class);
		UserRating usersRatings = restTemplate.getForObject("http://rating-data-service/users/"+userId, UserRating.class);

		return usersRatings.getRatings().stream().map(rating -> {
			//For each rating item above we create a new catalog item. Also The name
			//is coming from movie-info-service.			
			//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

			//Using WebClient in place of RestTemplate - Rest of the logic remains the same.
			//			Movie movie = webClientBuilder.build()
			//					.get()
			//					.uri("http://localhost:8082/movies/"+rating.getMovieId())
			//					.retrieve()
			//					.bodyToMono(Movie.class)
			//					.block();

			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}
}
