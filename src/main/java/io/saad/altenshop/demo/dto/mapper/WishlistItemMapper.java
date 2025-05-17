package io.saad.altenshop.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import io.saad.altenshop.demo.dto.WishlistItemDTO;
import io.saad.altenshop.demo.entity.WishlistItem;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING
		,unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface WishlistItemMapper {

	@Mapping(target = "productId", source = "product.id")
	WishlistItemDTO entityToWishlistItemDto(WishlistItem wishlistItem);

	@Mapping(target = "product.id", source = "productId")
	WishlistItem wishlistItemDtoToEntity(WishlistItemDTO wishlistItemDTO);
}
