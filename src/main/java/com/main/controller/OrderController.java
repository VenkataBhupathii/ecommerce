package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.exception.OrderException;
import com.main.exception.UserException;
import com.main.model.Address;
import com.main.model.OrderEntity;
import com.main.model.User;
import com.main.service.OrderService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<OrderEntity>createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization")String jwt)throws UserException{
				
		User user=userService.findUserProfileByJwt(jwt);
		
		OrderEntity order=orderService.createOrder(user, shippingAddress);
		
		
		
		return new ResponseEntity<OrderEntity>(order,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<OrderEntity>> usersOrderHistory(@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		List<OrderEntity>orders=orderService.usersOrderHistory(user.getId());
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderEntity> findOrderById(@PathVariable("id") Long orderId,
											@RequestHeader("Authorization")String jwt)throws UserException,OrderException{
					
		User user=userService.findUserProfileByJwt(jwt);
		
		OrderEntity order=orderService.findOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
	}
}
