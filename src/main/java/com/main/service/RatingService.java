package com.main.service;

import java.util.List;

import com.main.exception.ProductException;
import com.main.model.Rating;
import com.main.model.User;
import com.main.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req,User user)throws ProductException;
	public List<Rating> getProductsRating(Long productId);
}
