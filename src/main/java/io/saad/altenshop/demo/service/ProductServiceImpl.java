package io.saad.altenshop.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.saad.altenshop.demo.dto.PoductReadDTO;
import io.saad.altenshop.demo.dto.ProductWriteDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;
import io.saad.altenshop.demo.dto.mapper.ProductMapper;
import io.saad.altenshop.demo.entity.Product;
import io.saad.altenshop.demo.exception.model.ResourceNotFoundException;
import io.saad.altenshop.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	@Override
	public Page<PoductReadDTO> getAllProducts(Pageable pageable){
		return this.productRepository.findAll(pageable)
				.map(this.productMapper::entityToProductDTO);
	}

	@Override
	public PoductReadDTO getProductById(Long productId){
		return this.productRepository
				.findById(productId)
				.map(this.productMapper::entityToProductDTO)
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName() ,productId));
		
	}

	@Override
	public ProductResponseDTO createProduct(ProductWriteDTO productWriteDTO){
		
		Product newProduct = this.productMapper.productWriteDtoToEntity(productWriteDTO);
		
		Product savedProduct = this.productRepository.save(newProduct);
		
		return this.productMapper.entityToProductResponseDto(savedProduct);
	}

	@Override
	public ProductResponseDTO updateProduct(ProductWriteDTO productWriteDTO){
		
		Product existingProduct = this.productRepository.findById(productWriteDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName() ,productWriteDTO.getId()));
		
		this.productMapper.productWriteDtoToEntityUPDATE(productWriteDTO, existingProduct);
		
		Product updatedProduct = this.productRepository.save(existingProduct);
		
		return this.productMapper.entityToProductResponseDto(updatedProduct);
	}

	@Override
	public ProductResponseDTO deleteProduct(Long productId){
		Product productToDelete = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName() ,productId));
		
		ProductResponseDTO productResponseDTO = this.productMapper.entityToProductResponseDto(productToDelete);
		this.productRepository.delete(productToDelete);
		return productResponseDTO;
	}

}
