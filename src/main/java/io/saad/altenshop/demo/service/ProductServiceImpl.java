package io.saad.altenshop.demo.service;

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
	public Page<ProductDTO> getAllProducts(Pageable pageable){
		return this.productRepository.findAll(pageable)
				.map(this.productMapper::entityToProductDTO);
	}

	@Override
	public ProductDTO getProductById(Long productId){
		return this.productRepository
				.findById(productId)
				.map(this.productMapper::entityToProductDTO)
				.orElseThrow(() -> new RuntimeException("product not found"));
	}

	@Override
	public ProductResponseDTO createProduct(ProductFormDTO productFormDTO){
		
		Product newProduct = this.productMapper.productFormDtoToEntity(productFormDTO);
		
		Product savedProduct = this.productRepository.save(newProduct);
		
		return this.productMapper.entityToProductResponseDto(savedProduct);
	}

	@Override
	public ProductResponseDTO updateProduct(ProductFormDTO productFormDTO){
		
		Product existingProduct = this.productRepository.findById(productFormDTO.id())
									.orElseThrow(() -> 
										new RuntimeException("Problem during Product Update: product does not exist")
									);
		
		this.productMapper.productFormDtoToEntity(productFormDTO, existingProduct);
		
		Product updatedProduct = this.productRepository.save(existingProduct);
		
		return this.productMapper.entityToProductResponseDto(updatedProduct);
	}

	@Override
	public ProductResponseDTO deleteProduct(Long productId){
		Product productToDelete = this.productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Problem during Product Delete: product does not exist"));
		
		ProductResponseDTO productResponseDTO = this.productMapper.entityToProductResponseDto(productToDelete);
		this.productRepository.delete(productToDelete);
		return productResponseDTO;
	}

}
