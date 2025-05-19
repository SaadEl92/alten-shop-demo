package io.saad.altenshop.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemDTO (
    Long id,
    @NotNull Long productId,
    @Min(1) Integer quantity
) {}
