package io.saad.altenshop.demo.dto.mapper;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.entity.Product;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

	@Override
	public ProductDTO apply(Product product) {
		return ProductDTO.builder()
				.id(product.getId())
				.code(product.getCode())
				.name(product.getName())
				.description(product.getDescription())
				.image(product.getImage())
				.price(product.getPrice())
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
