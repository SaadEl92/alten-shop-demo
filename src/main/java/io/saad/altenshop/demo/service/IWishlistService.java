package io.saad.altenshop.demo.service;

import java.util.List;

import io.saad.altenshop.demo.dto.WishlistItemDTO;


public interface IWishlistService {
	List<WishlistItemDTO> getAllWishlistItemsByUser();
	
	WishlistItemDTO addToWishlist2(WishlistItemDTO wishlistItemDTO);

	WishlistItemDTO addToWishlist(WishlistItemDTO wishlistItemDTO);
	
	WishlistItemDTO removeFromWishlist(Long wishlistItemId);
}
