package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.exception.OrderException;
import com.main.model.OrderEntity;
import com.main.response.ApiResponse;
import com.main.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<OrderEntity>>getAllOrdersHandler(){
		List<OrderEntity> orders=orderService.getAllOrders();
		
		return new ResponseEntity<List<OrderEntity>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<OrderEntity> ConfirmedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)throws OrderException{
				
		OrderEntity order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<OrderEntity> ShippedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)throws OrderException{
		
		OrderEntity order=orderService.shippedOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<OrderEntity> DeliverOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)throws OrderException{
		
		OrderEntity order=orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<OrderEntity> CancelOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)throws OrderException{
		
		OrderEntity order=orderService.canceledOrder(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt)throws OrderException{
		
		orderService.deleteOrder(orderId);
		
		ApiResponse res=new ApiResponse();
		
		res.setMessage("order deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
}

