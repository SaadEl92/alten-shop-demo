package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.dto.mapper.WishlistItemMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.entity.Wishlist;
import io.saad.altenshop.demo.entity.WishlistItem;
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
	public List<WishlistItemDTO> getAllWishlistItemsByUserEmail(Principal principal) throws Exception {
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new Exception("can't find user"));
		
		Wishlist userWishlist = this.wishlistRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new Exception("can't find wishlist"));
		return userWishlist.getWishlistItems().stream().map(this.wishlistItemMapper::entityToWishlistItemDTO).toList();
	}

	@Override
	public WishlistItemDTO addToWishlist(Principal principal, WishlistItemDTO wishlistItemDTO) throws Exception {
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
									.orElseThrow(() -> new Exception("can't find user"));
		
		Wishlist wishlistOfUser = this.wishlistRepository.findById(authenticatedUser.getUserId())
									.orElseThrow(() -> new Exception("can't find wishlist"));
									
		Product choosenProduct = this.productRepository.findById(wishlistItemDTO.getProductId())
									.orElseThrow(() -> new Exception("can't find product"));
		
		WishlistItem wishlistItemToAdd = WishlistItem.builder()
									.product(choosenProduct)
									.wishlist(wishlistOfUser)
									.build();
		
		wishlistOfUser.addWishlistItem(wishlistItemToAdd);
		choosenProduct.addWishlistItem(wishlistItemToAdd);
								
		
		return Optional.of(this.wishlistItemRepository.save(wishlistItemToAdd))
				.map(this.wishlistItemMapper::entityToWishlistItemDTO)
				.orElseThrow(() -> new Exception("error creating the wishlistItem in database"));
	}

	@Override
	public WishlistItemDTO removeFromWishlist(Principal principal, Long wishlistItemId) throws Exception {
		WishlistItemDTO wishlistItemToDeleteDTO = this.wishlistItemRepository.findById(wishlistItemId)
				.map(this.wishlistItemMapper::entityToWishlistItemDTO)
				.orElseThrow(() -> new Exception("can't find wishlistItem"));
		
		this.wishlistItemRepository.deleteById(wishlistItemToDeleteDTO.getId());
		
		return wishlistItemToDeleteDTO;
	}

}
