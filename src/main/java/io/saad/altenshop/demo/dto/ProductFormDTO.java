package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;

import io.saad.altenshop.demo.entity.InventoryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record ProductFormDTO (
	
	Long id,
    String code,
    @NotBlank(message = "Product name can't be blank")
    String name,
    String description,
    String image,
    String category,
    @Digits(integer = 10, fraction = 2, message = "Price must be a number with up to 10 digits and 2 decimal places (ex: 55.99)")
    BigDecimal price,
    Integer quantity,
    String internalReference,
    Long shellId,
    @Enumerated(EnumType.STRING)
    InventoryStatus inventoryStatus,
    Integer rating
) {}
