package io.saad.altenshop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFeedbackDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.service.IProductService;
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
	public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) throws Exception {
		Page<ProductDTO> productsDtoPage = this.productService.getAllProducts(pageable);
		return new ResponseEntity<>(productsDtoPage, HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) throws Exception {
		ProductDTO productDto = this.productService.getProductById(productId);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductFeedbackDTO> createProduct(@RequestBody ProductFormDTO productToCreate) throws Exception {
		ProductFeedbackDTO createdProductFeedbackDto = this.productService.createProduct(productToCreate);

		return new ResponseEntity<>(createdProductFeedbackDto, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{productId}")
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductFeedbackDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductFormDTO productToUpdate) throws Exception {
		productToUpdate.setId(productId);
		ProductFeedbackDTO updatedProductFeedbackDto = this.productService.updateProduct(productToUpdate);

		return new ResponseEntity<>(updatedProductFeedbackDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	@PreAuthorize("authentication.name == 'admin@admin.com'")
	public ResponseEntity<ProductFeedbackDTO> deleteProduct(@PathVariable Long productId) throws Exception {
		ProductFeedbackDTO deletedProductFeedbackDto = this.productService.deleteProduct(productId);

		return new ResponseEntity<>(deletedProductFeedbackDto, HttpStatus.OK);
	}
	
	
}
