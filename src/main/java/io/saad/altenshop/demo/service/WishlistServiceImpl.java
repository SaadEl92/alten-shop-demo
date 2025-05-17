package io.saad.altenshop.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.dto.mapper.WishlistItemMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.entity.WishlistItem;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.repository.ProductRepository;
import io.saad.altenshop.demo.repository.WishlistItemRepository;
import io.saad.altenshop.demo.security.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWishlistService {
	
	private final ProductRepository productRepository;
	private final WishlistItemRepository wishlistItemRepository;

	private final UserService userService;
	
	private final WishlistItemMapper wishlistItemMapper;
	
	@Override
	public List<WishlistItemDTO> getAllWishlistItems(){
		Long authenticatedUserId = this.userService.authenticatedUserId();
		
		return this.wishlistItemRepository.fetchWishlistItemsOfAppUserById(authenticatedUserId)
					.stream()
					.map(this.wishlistItemMapper::entityToWishlistItemDto)
					.toList();
	}

	@Override
	public WishlistItemDTO addToWishlist(WishlistItemDTO wishlistItemDTO){
		AppUser authenticatedUser = this.userService.authenticatedUserEntity();
		Product choosenProduct = this.productRepository.findById(wishlistItemDTO.productId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), wishlistItemDTO.productId()));
		
		WishlistItem wishlistItemTosave = WishlistItem.builder()
									.appUser(authenticatedUser)
									.product(choosenProduct)
									.build();
		
		WishlistItem savedWishlistItem = this.wishlistItemRepository.save(wishlistItemTosave);
		
		return this.wishlistItemMapper.entityToWishlistItemDto(savedWishlistItem);
		
	}

	@Override
	public WishlistItemDTO removeFromWishlist(Long wishlistItemId){
		Long authenticatedUserId = this.userService.authenticatedUserId();
		WishlistItem wishlistItemToDelete = this.wishlistItemRepository.fetchWishlistItemByIdAndByAppUserId(wishlistItemId, authenticatedUserId)
				.orElseThrow(() -> new ResourceNotFoundException(WishlistItem.class.getSimpleName(), wishlistItemId));
		
		WishlistItemDTO wishlistItemToDeleteDTO = this.wishlistItemMapper.entityToWishlistItemDto(wishlistItemToDelete);
		
		this.wishlistItemRepository.delete(wishlistItemToDelete);
		
		return wishlistItemToDeleteDTO;
	}

}
