package io.saad.altenshop.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import io.saad.altenshop.demo.dto.PoductReadDTO;
import io.saad.altenshop.demo.dto.ProductWriteDTO;
import io.saad.altenshop.demo.dto.ProductResponseDTO;
import io.saad.altenshop.demo.entity.Product;

@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
	
	PoductReadDTO entityToProductDTO(Product product);
	
	ProductResponseDTO entityToProductResponseDto(Product product);

	@Mapping(target = "id", ignore = true)
	Product productWriteDtoToEntity(ProductWriteDTO productWriteDTO);
	
	void productWriteDtoToEntityUPDATE(ProductWriteDTO productWriteDTO, @MappingTarget Product product);
	
}
