package io.saad.altenshop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v0/products")
@RequiredArgsConstructor
public class ProductRestController {

	private final IProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable){
		Page<ProductDTO> productsDtoPage = this.productService.getAllProducts(pageable);
		return new ResponseEntity<>(productsDtoPage, HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
		ProductDTO productDto = this.productService.getProductById(productId);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductFormDTO productToCreate){
		ProductResponseDTO createdProductResponseDTO = this.productService.createProduct(productToCreate);
		return new ResponseEntity<>(createdProductResponseDTO, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{productId}")
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductFormDTO productToUpdate){
		/**
		 * The path variable productId is not used but it's required in the API Contract, the id is retrieved from the DTO 
		 */
		ProductResponseDTO updatedProductResponseDTO = this.productService.updateProduct(productToUpdate);
		return new ResponseEntity<>(updatedProductResponseDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long productId){
		ProductResponseDTO deletedProductResponseDTO = this.productService.deleteProduct(productId);
		return new ResponseEntity<>(deletedProductResponseDTO, HttpStatus.OK);
	}
	
	
}
