package com.microservicedemo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MovieCatalogServiceConfig {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		//Allows us to set a time-out to avoid slowing down the server and reclaim threads back on time.
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(); 
		clientHttpRequestFactory.setConnectTimeout(3000);
		return new RestTemplate(clientHttpRequestFactory);
	}

	//	@Bean
	//	public WebClient.Builder getWebClientBuilder() {
	//		return WebClient.builder();
	//	}	
}
