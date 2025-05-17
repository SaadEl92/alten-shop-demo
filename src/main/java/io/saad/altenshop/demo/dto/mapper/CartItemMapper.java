package io.saad.altenshop.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import io.saad.altenshop.demo.dto.CartItemDTO;
import io.saad.altenshop.demo.entity.CartItem;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING
		,unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CartItemMapper {

	@Mapping(target = "productId", source = "product.id")
	CartItemDTO entityToCartItemDTO(CartItem cartItem);
	
	@Mapping(target = "product.id", source = "productId")
	CartItem cartItemDtoToEntity(CartItemDTO cartItemDTO);
	
}
