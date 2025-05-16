package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;
import java.time.Instant;

import io.saad.altenshop.demo.entity.InventoryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public record ProductDTO (
	Long id,
    String code,
    String name,
    String description,
    String image,
    String category,
    BigDecimal price,
    Integer quantity,
    String internalReference,
    Long shellId,
    @Enumerated(EnumType.STRING)
    InventoryStatus inventoryStatus,
    Integer rating,
    Instant createdAt,
    Instant updatedAt
){}
