package io.saad.altenshop.demo.product.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.product.Product;
import io.saad.altenshop.demo.product.dto.ProductFeedbackDTO;

@Service
public class ProductFeedbackDTOMapper implements Function<Product, ProductFeedbackDTO> {

	@Override
	public ProductFeedbackDTO apply(Product product) {
		return ProductFeedbackDTO.builder()
				.id(product.getId())
				.code(product.getCode())
				.name(product.getName())
				.description(product.getDescription())
				.image(product.getImage())
				.build();
	}

}
