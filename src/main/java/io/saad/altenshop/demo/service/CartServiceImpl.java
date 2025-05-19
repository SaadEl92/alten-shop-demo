package io.saad.altenshop.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.dto.mapper.CartItemMapper;
import io.saad.altenshop.demo.entity.CartItem;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.repository.CartItemRepository;
import io.saad.altenshop.demo.repository.ProductRepository;
import io.saad.altenshop.demo.security.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
	
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	
	private final UserService userService;
	
	private final CartItemMapper cartItemMapper;

	@Override
	public List<CartItemDTO> getAllCartItems(){
		Long authenticatedUserId = this.userService.authenticatedUserId();
		
		return this.cartItemRepository.fetchCartItemsOfAppUserById(authenticatedUserId)
					.stream()
					.map(this.cartItemMapper::entityToCartItemDTO)
					.toList();
	}

	@Override
	public CartItemDTO addToCart(CartItemDTO cartItemDTO){
		AppUser authenticatedUser = this.userService.authenticatedUserEntity();
		Product choosenProduct = this.productRepository.findById(cartItemDTO.productId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), cartItemDTO.productId()));
		
		CartItem cartItemTosave = CartItem.builder()
									.quantity(cartItemDTO.quantity())
									.appUser(authenticatedUser)
									.product(choosenProduct)
									.build();
		
		CartItem savedCartItem = this.cartItemRepository.save(cartItemTosave);
		
		return this.cartItemMapper.entityToCartItemDTO(savedCartItem);
		
	}

	@Override
	public CartItemDTO removeFromCart(Long cartItemId){
		Long authenticatedUserId = this.userService.authenticatedUserId();
		CartItem cartItemToDelete = this.cartItemRepository.fetchCartItemByIdAndByAppUserId(cartItemId, authenticatedUserId)
				.orElseThrow(() -> new ResourceNotFoundException(CartItem.class.getSimpleName(), cartItemId));
		
		CartItemDTO cartItemToDeleteDTO = this.cartItemMapper.entityToCartItemDTO(cartItemToDelete);
		
		this.cartItemRepository.delete(cartItemToDelete);
		
		return cartItemToDeleteDTO;
	}

}
