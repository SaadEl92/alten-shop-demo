package io.saad.altenshop.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.service.ICartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v0/cart")
@RequiredArgsConstructor
public class CartRestController {
	
	private final ICartService cartService;
	
	@GetMapping
	public ResponseEntity<List<CartItemDTO>> getAllCartItemsForUser(Principal principal) throws Exception {
		List<CartItemDTO> cartItemsList = this.cartService.getAllCartItemsByUserEmail(principal);
		return new ResponseEntity<>(cartItemsList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CartItemDTO> addToCart(Principal principal, @RequestBody CartItemDTO cartItemDTO) throws Exception {
		CartItemDTO addedCartItem = this.cartService.addToCart(principal, cartItemDTO);
		return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<CartItemDTO> removeFromCart(Principal principal, @PathVariable Long cartItemId) throws Exception {
		CartItemDTO addedCartItem = this.cartService.removeFromCart(principal, cartItemId);
		return new ResponseEntity<>(addedCartItem, HttpStatus.OK);
	}

}
