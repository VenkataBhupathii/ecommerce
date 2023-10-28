package com.main.service;

import java.util.List;

import com.main.exception.ProductException;
import com.main.model.Review;
import com.main.model.User;
import com.main.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user)throws ProductException;
	public List<Review> getAllReview(Long productId);
}
