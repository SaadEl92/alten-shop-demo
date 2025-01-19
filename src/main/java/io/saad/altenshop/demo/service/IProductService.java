package io.saad.altenshop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFeedbackDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;

public interface IProductService {

	Page<ProductDTO> getAllProducts(Pageable pageable) throws Exception;
	
	ProductDTO getProductById(Long productId) throws Exception;
	
	ProductFeedbackDTO createProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductFeedbackDTO updateProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductFeedbackDTO deleteProduct(Long productId) throws Exception;
}
