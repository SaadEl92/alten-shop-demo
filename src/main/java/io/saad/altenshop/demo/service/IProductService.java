package io.saad.altenshop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;

public interface IProductService {

	Page<ProductDTO> getAllProducts(Pageable pageable);
	
	ProductDTO getProductById(Long productId);
	
	ProductResponseDTO createProduct(ProductFormDTO productFormDTO);

	ProductResponseDTO updateProduct(ProductFormDTO productFormDTO);

	ProductResponseDTO deleteProduct(Long productId);
}
