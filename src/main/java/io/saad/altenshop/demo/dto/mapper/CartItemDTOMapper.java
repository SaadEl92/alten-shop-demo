package io.saad.altenshop.demo.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.entity.CartItem;

@Service
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

	@Override
	public CartItemDTO apply(CartItem cartItem) {
		return CartItemDTO.builder()
				.id(cartItem.getId())
				.productId(cartItem.getProduct().getId())
				.quantity(cartItem.getQuantity())
				.build();
	}

}