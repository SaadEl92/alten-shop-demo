package io.saad.altenshop.demo.service;

import java.util.List;

import io.saad.altenshop.demo.dto.WishlistItemDTO;


public interface IWishlistService {
	List<WishlistItemDTO> getAllWishlistItems();

	WishlistItemDTO addToWishlist(WishlistItemDTO wishlistItemDTO);
	
	WishlistItemDTO removeFromWishlist(Long wishlistItemId);
}
