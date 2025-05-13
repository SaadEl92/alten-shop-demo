package io.saad.altenshop.demo.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.dto.mapper.CartItemMapper;
import io.saad.altenshop.demo.entity.Cart;
import io.saad.altenshop.demo.entity.CartItem;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.entity.User;
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
	public List<CartItemDTO> getAllCartItemsByUserEmail(Principal principal) throws Exception {
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new Exception("can't find user"));
		
		Cart userCart = this.cartRepository.findById(authenticatedUser.getUserId())
				.orElseThrow(() -> new Exception("can't find cart"));
		
		return userCart.getCartItems().stream().map(this.cartItemMapper::entityToCartItemDTO).toList();
	}

	@Override
	public CartItemDTO addToCart(Principal principal, CartItemDTO cartItemDTO) throws Exception {
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
									.orElseThrow(() -> new Exception("can't find user"));
		
		Cart cartOfUser = this.cartRepository.findById(authenticatedUser.getUserId())
									.orElseThrow(() -> new Exception("can't find cart"));
									
		Product choosenProduct = this.productRepository.findById(cartItemDTO.productId())
									.orElseThrow(() -> new Exception("can't find product"));
		
		CartItem cartItemToAdd = CartItem.builder()
									.product(choosenProduct)
									.cart(cartOfUser)
									.quantity(cartItemDTO.quantity())
									.build();
		
		cartOfUser.addCartItem(cartItemToAdd);
		choosenProduct.addCartItem(cartItemToAdd);
								
		
		return Optional.of(this.cartItemRepository.save(cartItemToAdd))
				.map(this.cartItemMapper::entityToCartItemDTO)
				.orElseThrow(() -> new Exception("error creating the cartItem in database"));
	}

	@Override
	public CartItemDTO removeFromCart(Principal principal, Long cartItemId) throws Exception {
		CartItemDTO cartItemToDeleteDTO = this.cartItemRepository.findById(cartItemId)
				.map(this.cartItemMapper::entityToCartItemDTO)
				.orElseThrow(() -> new Exception("can't find cartItem"));
		
		this.cartItemRepository.deleteById(cartItemToDeleteDTO.id());
		
		return cartItemToDeleteDTO;
	}

}
