package com.main.service;

import com.main.exception.ProductException;
import com.main.model.Cart;
import com.main.model.User;
import com.main.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long UserId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
