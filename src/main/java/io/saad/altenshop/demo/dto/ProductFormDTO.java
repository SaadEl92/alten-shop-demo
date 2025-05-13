package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;

import io.saad.altenshop.demo.entity.InventoryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductFormDTO (
	
	Long id,
    String code,
    @NotNull(message = "Product name can't be null")
    @NotEmpty(message = "Product name can't be empty")
    String name,
    String description,
    String image,
    String category,
    @NotNull(message = "Product price can't be null")
    @NotEmpty(message = "Product price can't be empty")
    BigDecimal price,
    Integer quantity,
    String internalReference,
    Long shellId,
    @Enumerated(EnumType.STRING)
    InventoryStatus inventoryStatus,
    Integer rating
) {}
