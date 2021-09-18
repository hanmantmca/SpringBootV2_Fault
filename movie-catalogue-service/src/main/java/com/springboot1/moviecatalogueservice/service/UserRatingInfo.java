package com.springboot1.moviecatalogueservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springboot1.moviecatalogueservice.model.Rating;
import com.springboot1.moviecatalogueservice.model.UserRating;

@Service
public class UserRatingInfo {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating",
			commandProperties = {
			        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
			        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") }, threadPoolProperties = {
			        @HystrixProperty(name = "coreSize", value = "30"),
			        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") })
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://movie-rating-service/movieRatings/user/"+ userId , UserRating.class);
	}
	
	private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		
		UserRating userRating = new UserRating();
		userRating.setUserReating(Arrays.asList(new Rating("Movie1", 1)));
		return userRating;
	}
}
