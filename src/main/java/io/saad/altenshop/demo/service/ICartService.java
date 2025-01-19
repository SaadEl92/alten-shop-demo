package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import io.saad.altenshop.demo.dto.CartItemDTO;

public interface ICartService {
	
	List<CartItemDTO> getAllCartItemsByUserEmail(Principal principal) throws Exception;
	
	CartItemDTO addToCart(Principal principal, CartItemDTO cartItemDTO)  throws Exception;
	
	CartItemDTO removeFromCart(Principal principal, Long cartItemId)  throws Exception;
	

}
