package io.saad.altenshop.demo.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.ProductFeedbackDTO;
import io.saad.altenshop.demo.entity.Product;

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
