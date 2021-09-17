package com.springboot1.movieratingservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot1.movieratingservice.model.Rating;
import com.springboot1.movieratingservice.model.UserRating;

@RestController
@RequestMapping("/movieRatings")
public class MovieRatingResource {

	@GetMapping("/{movieId}")
	public Rating getRatings(@PathVariable String movieId ){
		return new Rating(movieId, 4);
	}
	
	@GetMapping("/user/{movieId}")
	public UserRating getUserRatings(@PathVariable String movieId ){
	List<Rating> movieRatings = Arrays.asList(new Rating("1234", 4),
			new Rating("4567", 3));
	
	UserRating uRating = new UserRating();
	 uRating.setUserReating(movieRatings);
	 
	return uRating;
	}
}
