package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import io.saad.altenshop.demo.dto.WishlistItemDTO;


public interface IWishlistService {
	List<WishlistItemDTO> getAllWishlistItemsByUserEmail(Principal principal);
	
	WishlistItemDTO addToWishlist(Principal principal, WishlistItemDTO wishlistItemDTO);
	
	WishlistItemDTO removeFromWishlist(Principal principal, Long wishlistItemId);
}
