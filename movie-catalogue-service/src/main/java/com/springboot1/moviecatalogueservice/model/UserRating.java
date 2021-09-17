package com.springboot1.moviecatalogueservice.model;

import java.util.List;

public class UserRating {
	
	private List<Rating> userReating;
	
	public UserRating() {
		
	}

	public List<Rating> getUserReating() {
		return userReating;
	}

	public void setUserReating(List<Rating> userReating) {
		this.userReating = userReating;
	}
	
}
