package com.microservicedemo.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicedemo.models.CatalogItem;

@RestController
@RequestMapping("/catalog")   //Root URL - Redirect any URL starting with /catalog to this class
public class MovieCatalogResource {
	
	@RequestMapping("{/userId}") // equivalent to - /catalog/userId - /catalog comes from class level.
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		return Collections.singletonList(
					new CatalogItem("Transformers", "Test", 4)
				);
	}
}
