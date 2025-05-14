package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.dto.mapper.CartItemMapper;
import io.saad.altenshop.demo.entity.Cart;
import io.saad.altenshop.demo.entity.CartItem;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.repository.CartItemRepository;
import io.saad.altenshop.demo.repository.CartRepository;
import io.saad.altenshop.demo.repository.ProductRepository;
import io.saad.altenshop.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
	
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	
	private final CartItemMapper cartItemMapper;

	@Override
	public List<CartItemDTO> getAllCartItemsByUserEmail(Principal principal){
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new RuntimeException("can't find user"));
		
		Cart userCart = this.cartRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(Cart.class.getSimpleName(), authenticatedUser.getUserId()));
		
		return userCart.getCartItems()
				.stream()
				.map(this.cartItemMapper::entityToCartItemDTO)
				.toList();
	}

	@Override
	public CartItemDTO addToCart(Principal principal, CartItemDTO cartItemDTO){
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new RuntimeException("can't find user"));
		
		Cart cartOfUser = this.cartRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException(Cart.class.getSimpleName(), authenticatedUser.getUserId()));
									
		Product choosenProduct = this.productRepository.findById(cartItemDTO.productId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), cartItemDTO.productId()));
		
		CartItem cartItemToAdd = this.cartItemMapper.cartItemDtoToEntity(cartItemDTO);
		
		/**
		 * Keep the parent-side of the bidirectional relationships in sync
		 * Cart <-> CartItem
		 * Product <-> CartItem
		 */
		cartOfUser.addCartItem(cartItemToAdd);
		choosenProduct.addCartItem(cartItemToAdd);
								
		CartItem savedCartItem = this.cartItemRepository.save(cartItemToAdd);
		
		return this.cartItemMapper.entityToCartItemDTO(savedCartItem);
	}

	@Override
	public CartItemDTO removeFromCart(Principal principal, Long cartItemId){
		CartItem cartItemToDelete = this.cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException(CartItem.class.getSimpleName(), cartItemId));
		
		CartItemDTO cartItemToDeleteDTO = this.cartItemMapper.entityToCartItemDTO(cartItemToDelete);
		
		this.cartItemRepository.delete(cartItemToDelete);
		
		return cartItemToDeleteDTO;
	}

}
