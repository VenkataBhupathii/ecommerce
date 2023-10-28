package com.main.service;

import com.main.exception.CartItemException;
import com.main.exception.UserException;
import com.main.model.Cart;
import com.main.model.CartItem;
import com.main.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException,UserException;

	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
}
