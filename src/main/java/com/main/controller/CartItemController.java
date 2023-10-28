package com.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.exception.CartItemException;
import com.main.exception.ProductException;
import com.main.exception.UserException;
import com.main.model.Cart;
import com.main.model.CartItem;
import com.main.model.Product;
import com.main.model.User;
import com.main.request.AddItemRequest;
import com.main.response.ApiResponse;
import com.main.service.CartItemService;
import com.main.service.CartService;
import com.main.service.UserService;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private CartItemService cartItemService;
    private UserService userService;
    private CartService cartService;

    public CartItemController(CartItemService cartItemService, UserService userService,CartService cartService) {
        this.cartItemService = cartItemService;
        this.userService = userService;
        this.cartService=cartService;
    }
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
    		@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
				
    	User user=userService.findUserProfileByJwt(jwt);
    	cartItemService.removeCartItem(user.getId(), cartItemId);
    	
    	ApiResponse res=new ApiResponse();
    	res.setMessage("delete item from cart");
    	res.setStatus(true);
    	return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        CartItem createdCartItem = cartItemService.createCartItem(cartItem);
        return new ResponseEntity<>(createdCartItem, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItem(userId, cartItemId, cartItem);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (CartItemException | UserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/check-existence")
    public ResponseEntity<CartItem> isCartItemExist(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestParam Long userId) {
        Cart cart = new Cart(); 
        Product product = new Product(); 
        CartItem cartItem = cartItemService.isCartItemExist(cart, product, size, userId);
        if (cartItem != null) {
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long cartItemId) {
        try {
            CartItem cartItem = cartItemService.findCartItemById(cartItemId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (CartItemException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
    		@RequestHeader("Authorization")String jwt)throws UserException,CartItemException, ProductException{
				
    	User user=userService.findUserProfileByJwt(jwt);
    	cartService.addCartItem(user.getId(), req);
    	
    	ApiResponse res=new ApiResponse();
    	res.setMessage(" item added to cart");
    	res.setStatus(true);
    	return new ResponseEntity<>(res,HttpStatus.OK);
    }
}

