package com.springboot1.moviecatalogueservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot1.moviecatalogueservice.model.CatalogItem;
import com.springboot1.moviecatalogueservice.model.Movie;
import com.springboot1.moviecatalogueservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movieInfo/"+ rating.getMovieId() , Movie.class);
		
		return	new CatalogItem(movie.getName(), movie.getName() + " Desc", rating.getRating());
	}
	
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}
}
