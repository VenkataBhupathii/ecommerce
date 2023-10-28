package com.main.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.main.exception.ProductException;
import com.main.model.Product;
import com.main.model.Rating;
import com.main.model.User;
import com.main.repository.RatingRepository;
import com.main.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductService productService;

	public RatingServiceImpl(RatingRepository ratingRepository, ProductService productService) {
		
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		
		Product product=productService.findProductById(req.getProductId());
		Rating rating=new Rating();
		
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(0);
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
