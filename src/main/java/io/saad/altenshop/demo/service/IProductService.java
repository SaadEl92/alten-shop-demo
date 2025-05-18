package io.saad.altenshop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.saad.altenshop.demo.dto.PoductReadDTO;
import io.saad.altenshop.demo.dto.ProductWriteDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;

public interface IProductService {

	Page<PoductReadDTO> getAllProducts(Pageable pageable);
	
	PoductReadDTO getProductById(Long productId);
	
	ProductResponseDTO createProduct(ProductWriteDTO productWriteDTO);

	ProductResponseDTO updateProduct(ProductWriteDTO productWriteDTO);

	ProductResponseDTO deleteProduct(Long productId);
}
