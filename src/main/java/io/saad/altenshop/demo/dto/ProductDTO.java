package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;

import io.saad.altenshop.demo.entity.InventoryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDTO {
	private Long id;
    
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;
    
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    
    private Integer rating;
    
    private Long createdAt;
    private Long updatedAt;
}
