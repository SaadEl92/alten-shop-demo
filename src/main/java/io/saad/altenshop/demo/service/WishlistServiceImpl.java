package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.dto.mapper.WishlistItemMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.entity.Wishlist;
import io.saad.altenshop.demo.entity.WishlistItem;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.repository.ProductRepository;
import io.saad.altenshop.demo.repository.UserRepository;
import io.saad.altenshop.demo.repository.WishlistItemRepository;
import io.saad.altenshop.demo.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWishlistService {
	
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final WishlistRepository wishlistRepository;
	private final WishlistItemRepository wishlistItemRepository;
	
	private final WishlistItemMapper wishlistItemMapper;

	@Override
	public List<WishlistItemDTO> getAllWishlistItemsByUserEmail(Principal principal){
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new RuntimeException("can't find user"));
		
		Wishlist userWishlist = this.wishlistRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(Wishlist.class.getSimpleName(), authenticatedUser.getUserId()));
		
		return userWishlist.getWishlistItems()
				.stream()
				.map(this.wishlistItemMapper::entityToWishlistItemDto)
				.toList();
	}

	@Override
	public WishlistItemDTO addToWishlist(Principal principal, WishlistItemDTO wishlistItemDTO){
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new RuntimeException("can't find user"));
		
		Wishlist wishlistOfUser = this.wishlistRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(Wishlist.class.getSimpleName(), authenticatedUser.getUserId()));
		
		Product choosenProduct = this.productRepository.findById(wishlistItemDTO.productId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), wishlistItemDTO.productId()));
		
		WishlistItem wishlistItemToAdd = this.wishlistItemMapper.wishlistItemDtoToEntity(wishlistItemDTO);
		
		/**
		 * Keep the parent-side of the bidirectional relationships in sync
		 * Wishlist <-> WishlistItem
		 * Product <-> WishlistItem
		 */
		wishlistOfUser.addWishlistItem(wishlistItemToAdd);
		choosenProduct.addWishlistItem(wishlistItemToAdd);
								
		WishlistItem savedWishlistItem = this.wishlistItemRepository.save(wishlistItemToAdd);
		
		return this.wishlistItemMapper.entityToWishlistItemDto(savedWishlistItem);
	}

	@Override
	public WishlistItemDTO removeFromWishlist(Principal principal, Long wishlistItemId){
		WishlistItem wishlistItemToDelete = this.wishlistItemRepository.findById(wishlistItemId)
				.orElseThrow(() -> new ResourceNotFoundException(WishlistItem.class.getSimpleName(), wishlistItemId));
		
		WishlistItemDTO wishlistItemToDeleteDTO = this.wishlistItemMapper.entityToWishlistItemDto(wishlistItemToDelete);
		
		this.wishlistItemRepository.delete(wishlistItemToDelete);
		
		return wishlistItemToDeleteDTO;
	}

}
