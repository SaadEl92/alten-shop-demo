package io.saad.altenshop.demo.controller;

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

import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.service.IWishlistService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v0/wishlist")
@RequiredArgsConstructor
public class WishlistRestController {
	
	private final IWishlistService wishlistService;
	
	@GetMapping
	public ResponseEntity<List<WishlistItemDTO>> getAllWishlistItemsForUser() {
		List<WishlistItemDTO> wishlistItemsList = this.wishlistService.getAllWishlistItems();
		return new ResponseEntity<>(wishlistItemsList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<WishlistItemDTO> addToWishlist(@RequestBody WishlistItemDTO wishlistItemDTO) {
		WishlistItemDTO addedWishlistItem = this.wishlistService.addToWishlist(wishlistItemDTO);
		return new ResponseEntity<>(addedWishlistItem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{wishlistItemId}")
	public ResponseEntity<WishlistItemDTO> removeFromWishlist(@PathVariable Long wishlistItemId) {
		WishlistItemDTO addedWishlistItem = this.wishlistService.removeFromWishlist(wishlistItemId);
		return new ResponseEntity<>(addedWishlistItem, HttpStatus.OK);
	}

}
