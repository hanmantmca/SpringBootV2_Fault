package com.springboot1.moviecatalogueservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.springboot1.moviecatalogueservice.model.CatalogItem;
import com.springboot1.moviecatalogueservice.model.Movie;
import com.springboot1.moviecatalogueservice.model.Rating;
import com.springboot1.moviecatalogueservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogs(@PathVariable String userId){
		
		
		UserRating userRating = restTemplate.getForObject("http://movie-rating-service/movieRatings/user/"+ userId , UserRating.class);  
		
		return userRating.getUserReating().stream().map(rating ->{
			Movie movie = restTemplate.getForObject("http://movie-info-service/movieInfo/"+rating.getMovieId() , Movie.class);
			
		return	new CatalogItem(movie.getName(), movie.getName() + " Desc", rating.getRating());
		}).collect(Collectors.toList());
		
	}

}

/*
 * Movie movie = webClientBuilder.build() .get()
 * .uri("http://localhost:8082/movieInfo/"+ rating.getMovieId()) .retrieve()
 * .bodyToMono(Movie.class) .block();
 */
