package io.saad.altenshop.demo.product.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.product.Product;
import io.saad.altenshop.demo.product.dto.ProductDTO;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

	@Override
	public ProductDTO apply(Product product) {
		return ProductDTO.builder()
				.id(product.getId())
				.code(product.getCode())
				.description(product.getDescription())
				.image(product.getImage())
				.category(product.getCategory())
				.quantity(product.getQuantity())
				.internalReference(product.getInternalReference())
				.shellId(product.getShellId())
				.inventoryStatus(product.getInventoryStatus())
				.rating(product.getRating())
				.createdAt(product.getCreatedAt())
				.updatedAt(product.getUpdatedAt())
				.build();
	}

}
