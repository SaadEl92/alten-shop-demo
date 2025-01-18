package io.saad.altenshop.demo.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.entity.WishlistItem;

@Service
public class WishlistItemDTOMapper implements Function<WishlistItem, WishlistItemDTO>{

	@Override
	public WishlistItemDTO apply(WishlistItem wishlistItem) {
		return WishlistItemDTO.builder()
				.id(wishlistItem.getId())
				.productId(wishlistItem.getProduct().getId())
				.build();
	}

}
