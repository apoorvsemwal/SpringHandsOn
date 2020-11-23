package com.microservicedemo.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicedemo.models.Rating;
import com.microservicedemo.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "12000")
	})
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/users/"+userId, UserRating.class);
	}

	public UserRating getFallbackUserRating(String userId) {
		UserRating rating = new UserRating();
		rating.setUserId(userId);
		rating.setRatings(Arrays.asList(new Rating("0", 0)));
		return rating;
	}
}
