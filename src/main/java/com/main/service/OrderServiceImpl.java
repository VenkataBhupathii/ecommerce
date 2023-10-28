package com.main.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.main.exception.OrderException;
import com.main.model.Address;
import com.main.model.Cart;
import com.main.model.CartItem;
import com.main.model.OrderEntity;
import com.main.model.OrderItem;
import com.main.model.User;
import com.main.repository.AddressRepository;
import com.main.repository.CartRepository;
import com.main.repository.OrderRepository;
import com.main.repository.UserRepository;
import com.main.repository.OrderItemRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;
	private UserRepository userRepository;
	private AddressRepository addressRepository;
	private CartService cartService;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	
	

	public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
			AddressRepository addressRepository, CartService cartService, OrderItemService orderItemService,
			OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.cartService = cartService;
		this.orderItemService = orderItemService;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public OrderEntity createOrder(User user, Address shippingAddress) {
		
		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		
		user.getAddresses().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item:cart.getCartItems()) {
			
			OrderItem orderitem=new OrderItem();
			
			orderitem.setPrice(item.getPrice());
			orderitem.setProduct(item.getProduct());
			orderitem.setQuantity(item.getQuantity());
			orderitem.setSize(item.getSize());
			orderitem.setUserId(item.getUserId());
			orderitem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderitem);
			
			orderItems.add(createdOrderItem);
		}
		OrderEntity createdOrder=new OrderEntity();
		createdOrder.setUser(user);
		createdOrder.setOrderitems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setCreateAt(LocalDateTime.now());
		
		OrderEntity savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		 
		return savedOrder;
}

	@Override
	public OrderEntity findOrderById (Long orderId) throws OrderException {
		
		Optional<OrderEntity> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new OrderException("order not exist with id:" +orderId);
	}

	@Override
	public List<OrderEntity> usersOrderHistory(Long userId) {
		
		List<OrderEntity> orders=orderRepository.getUsersOrders(userId);
		
		return orders;
	}

	@Override
	public OrderEntity placedOrder(Long orderId) throws OrderException {
		
		OrderEntity orderEntity=findOrderById(orderId);
		orderEntity.setOrderStatus("ORDER PLACED");
		orderEntity.getPaymentDetails().setStatus("COMPLETED");
		return orderEntity;
	}

	@Override
	public OrderEntity confirmedOrder(Long orderId) throws OrderException {
		
		OrderEntity orderEntity=findOrderById(orderId);
		orderEntity.setOrderStatus("CONFIRMED");
		
		return orderRepository.save(orderEntity);
	}

	@Override
	public OrderEntity shippedOrder(Long orderId) throws OrderException {
		
		OrderEntity orderEntity=findOrderById(orderId);
		orderEntity.setOrderStatus("SHIPPED");
		
		return orderRepository.save(orderEntity);
	}

	@Override
	public OrderEntity deliveredOrder(Long orderId) throws OrderException {
		OrderEntity orderEntity=findOrderById(orderId);
		orderEntity.setOrderStatus("DELIVERED");
		
		return orderRepository.save(orderEntity);
	}

	@Override
	public List<OrderEntity> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		
		OrderEntity orderEntity=findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
	}

	@Override
	public OrderEntity canceledOrder(Long orderId) throws OrderException {
		
		OrderEntity orderEntity=findOrderById(orderId);
		orderEntity.setOrderStatus("CANCELED ORDER");
		
		return orderRepository.save(orderEntity);
	}
	
}
