package io.saad.altenshop.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.dto.mapper.WishlistItemMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.entity.Wishlist;
import io.saad.altenshop.demo.entity.WishlistItem;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.exception.model.AppUserNotFoundException;
import io.saad.altenshop.demo.exception.model.ForbiddenResourceException;
import io.saad.altenshop.demo.repository.ProductRepository;
import io.saad.altenshop.demo.repository.UserRepository;
import io.saad.altenshop.demo.repository.WishlistItemRepository;
import io.saad.altenshop.demo.repository.WishlistRepository;
import io.saad.altenshop.demo.security.IAuthenticationFacade;
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
	
	private final IAuthenticationFacade authenticationFacade;
	
	private Wishlist findWishListByUserId() {
		Authentication authentication = authenticationFacade.getAuthentication();
		AppUser authenticatedUser = this.userRepository.findByEmail(authentication.getName())
				.orElseThrow(AppUserNotFoundException::new);
		
		return this.wishlistRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(Wishlist.class.getSimpleName(), authenticatedUser.getUserId()));
	}
	
	private Wishlist findWishListByUserId2() {
		Authentication authentication = authenticationFacade.getAuthentication();
		AppUser authenticatedUser = this.userRepository.findByEmail(authentication.getName())
				.orElseThrow(AppUserNotFoundException::new);
		
		return this.wishlistRepository.getReferenceById(authenticatedUser.getUserId());
	}

	@Override
	public List<WishlistItemDTO> getAllWishlistItemsByUser(){
		
		Wishlist wishlistOfUser = this.findWishListByUserId();
		
		return wishlistOfUser.getWishlistItems()
				.stream()
				.map(this.wishlistItemMapper::entityToWishlistItemDto)
				.toList();
	}

	@Override
	public WishlistItemDTO addToWishlist(WishlistItemDTO wishlistItemDTO){
		
		Wishlist wishlistOfUser = this.findWishListByUserId();
		
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
	public WishlistItemDTO addToWishlist2(WishlistItemDTO wishlistItemDTO){
		
		return null;
	}

	@Override
	public WishlistItemDTO removeFromWishlist(Long wishlistItemId){
		
		Wishlist wishlistOfUser = this.findWishListByUserId();
		
		wishlistOfUser.getWishlistItems().stream()
			.filter(wishlistItem -> Objects.equals(wishlistItem.getId(), wishlistItemId))
			.findFirst()
			.orElseThrow(ForbiddenResourceException::new);
		
		WishlistItem wishlistItemToDelete = this.wishlistItemRepository.findById(wishlistItemId)
				.orElseThrow(() -> new ResourceNotFoundException(WishlistItem.class.getSimpleName(), wishlistItemId));
		
		/**
		 * Keep the parent-side of the bidirectional relationships in sync
		 * Wishlist <-> WishlistItem
		 * Product <-> WishlistItem
		 */
		Product choosenProduct = this.productRepository.findById(wishlistItemToDelete.getProduct().getId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), wishlistItemToDelete.getProduct().getId()));
		
		wishlistOfUser.removeWishlistItem(wishlistItemToDelete);
		choosenProduct.removeWishlistItem(wishlistItemToDelete);
		
		WishlistItemDTO wishlistItemToDeleteDTO = this.wishlistItemMapper.entityToWishlistItemDto(wishlistItemToDelete);
		
		this.wishlistItemRepository.delete(wishlistItemToDelete);
		
		return wishlistItemToDeleteDTO;
	}

}
