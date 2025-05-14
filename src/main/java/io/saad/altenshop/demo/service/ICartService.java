package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import io.saad.altenshop.demo.dto.CartItemDTO;

public interface ICartService {
	
	List<CartItemDTO> getAllCartItemsByUserEmail(Principal principal);
	
	CartItemDTO addToCart(Principal principal, CartItemDTO cartItemDTO);
	
	CartItemDTO removeFromCart(Principal principal, Long cartItemId);
	

}
