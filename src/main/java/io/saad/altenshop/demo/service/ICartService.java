package io.saad.altenshop.demo.service;

import java.util.List;

import io.saad.altenshop.demo.dto.CartItemDTO;

public interface ICartService {
	
	List<CartItemDTO> getAllCartItems();
	
	CartItemDTO addToCart(CartItemDTO cartItemDTO);
	
	CartItemDTO removeFromCart(Long cartItemId);
	

}
