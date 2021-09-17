package com.springboot1.movieinfoservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot1.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movieInfo")
public class MovieInforResource {

	@GetMapping("/{movieId}")
	public Movie getMovies(@PathVariable String movieId){
		
		return new Movie(movieId, "Test Movie");
		
	}
}
