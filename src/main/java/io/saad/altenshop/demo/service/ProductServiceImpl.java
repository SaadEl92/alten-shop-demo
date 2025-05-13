package io.saad.altenshop.demo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;
import io.saad.altenshop.demo.dto.mapper.ProductMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;
	
	private final ProductMapper productMapper;
	
	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageable) throws Exception {
		Page<Product> productsPage = this.productRepository.findAll(pageable);
		return productsPage.map(this.productMapper::entityToProductDTO);
	}

	@Override
	public ProductDTO getProductById(Long productId) throws Exception {
		return this.productRepository
				.findById(productId)
				.map(this.productMapper::entityToProductDTO)
				.orElseThrow(() -> new Exception("product not found"));
	}

	@Override
	public ProductResponseDTO createProduct(ProductFormDTO productFormDTO) throws Exception {
		return Optional.of(productFormDTO)
				.map(this.productMapper::productFormDtoToEntity)
				.map(this.productRepository::save)
				.map(this.productMapper::entityToProductResponseDto)
				.orElseThrow(() -> new Exception("Problem: product not created in database"));
	}

	@Override
	public ProductResponseDTO updateProduct(ProductFormDTO productFormDTO) throws Exception {
		if(!this.productRepository.existsById(productFormDTO.id()))
			throw new Exception("Problem during Product Update: product does not exist");
		
		return Optional.of(productFormDTO)
				.map(this.productMapper::productFormDtoToEntity)
				.map(this.productRepository::save)
				.map(this.productMapper::entityToProductResponseDto)
				.orElseThrow(() -> new Exception("Problem: product not updated in database"));
	}

	@Override
	public ProductResponseDTO deleteProduct(Long productId) throws Exception {
		Product productToDelete = this.productRepository.findById(productId)
				.orElseThrow(() -> new Exception("Problem during Product Delete: product does not exist"));
		
		ProductResponseDTO productResponseDTO = Optional.of(productToDelete)
													.map(this.productMapper::entityToProductResponseDto)
													.orElseThrow(() -> new Exception("productFeedbackDTOMapper Error"));
		
		
		this.productRepository.delete(productToDelete);
		return productResponseDTO;
	}

}
