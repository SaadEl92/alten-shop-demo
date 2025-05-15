package io.saad.altenshop.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import io.saad.altenshop.demo.dto.ProductDTO;
import io.saad.altenshop.demo.dto.ProductFormDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;
import io.saad.altenshop.demo.entity.Product;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
	
	ProductDTO entityToProductDTO(Product product);
	
	ProductResponseDTO entityToProductResponseDto(Product product);
	
	Product productFormDtoToEntity(ProductFormDTO productFormDTO);
	
	void productFormDtoToEntity(ProductFormDTO productFormDTO, @MappingTarget Product product);
	
}
