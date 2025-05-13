package io.saad.altenshop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;

public interface IProductService {

	Page<ProductDTO> getAllProducts(Pageable pageable) throws Exception;
	
	ProductDTO getProductById(Long productId) throws Exception;
	
	ProductResponseDTO createProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductResponseDTO updateProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductResponseDTO deleteProduct(Long productId) throws Exception;
}
