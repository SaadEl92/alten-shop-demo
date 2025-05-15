package io.saad.altenshop.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.entity.WishlistItem;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface WishlistItemMapper {

	WishlistItemDTO entityToWishlistItemDto(WishlistItem wishlistItem);
	
	WishlistItem wishlistItemDtoToEntity(WishlistItemDTO wishlistItemDTO);
}
