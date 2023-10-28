package com.main.service;

import java.util.List;

import com.main.exception.OrderException;
import com.main.model.Address;
import com.main.model.OrderEntity;
import com.main.model.User;

public interface OrderService {
	public OrderEntity createOrder(User user,Address shippingAddress);
	public OrderEntity findOrderById(Long orderId)throws OrderException;
	public List<OrderEntity>usersOrderHistory(Long userId);
	public OrderEntity placedOrder(Long orderId)throws OrderException;
	public OrderEntity confirmedOrder(Long orderId)throws OrderException;
	public OrderEntity shippedOrder(Long orderId)throws OrderException;
	public OrderEntity deliveredOrder(Long orderId)throws OrderException;
	public List<OrderEntity> getAllOrders();
	public void deleteOrder(Long orderId)throws OrderException;
	public OrderEntity canceledOrder(Long orderId) throws OrderException;
}
