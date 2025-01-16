package io.saad.altenshop.demo.product;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.product.dto.ProductFormDTO;

@Service
public class ProductMapper implements Function<ProductFormDTO, Product> {

	@Override
	public Product apply(ProductFormDTO productFormDto) {
		return Product.builder()
				.id(productFormDto.getId())
				.code(productFormDto.getCode())
				.name(productFormDto.getName())
				.description(productFormDto.getDescription())
				.image(productFormDto.getImage())
				.category(productFormDto.getCategory())
				.price(productFormDto.getPrice())
				.quantity(productFormDto.getQuantity())
				.internalReference(productFormDto.getInternalReference())
				.shellId(productFormDto.getShellId())
				.inventoryStatus(productFormDto.getInventoryStatus())
				.rating(productFormDto.getRating())
				.build();
	}

}
