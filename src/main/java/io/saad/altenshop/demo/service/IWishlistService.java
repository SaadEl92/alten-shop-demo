package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import io.saad.altenshop.demo.dto.WishlistItemDTO;


public interface IWishlistService {
	List<WishlistItemDTO> getAllWishlistItemsByUserEmail(Principal principal) throws Exception;
	
	WishlistItemDTO addToWishlist(Principal principal, WishlistItemDTO wishlistItemDTO)  throws Exception;
	
	WishlistItemDTO removeFromWishlist(Principal principal, Long wishlistItemId)  throws Exception;
}
