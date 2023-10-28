package com.main.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.main.exception.ProductException;
import com.main.model.Product;
import com.main.model.Review;
import com.main.model.User;
import com.main.repository.ProductRepository;
import com.main.repository.ReviewRepository;
import com.main.request.ReviewRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService) {

		this.reviewRepository = reviewRepository;
		this.productService = productService;
		
	}
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product=productService.findProductById(req.getProductId());
		
		Review review=new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

	

}
