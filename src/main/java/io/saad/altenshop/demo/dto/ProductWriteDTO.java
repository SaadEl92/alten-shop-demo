package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;

import io.saad.altenshop.demo.entity.InventoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record ProductWriteDTO (
	
	Long id,
	@Schema(example = "f230fh0g3")
    String code,
    @NotBlank(message = "Product name can't be blank")
	@Schema(example = "Samsung TV")
    String name,
	@Schema(example = "OLED 4K")
    String description,
	@Schema(example = "image.jpg")
    String image,
	@Schema(example = "TV & Monitors")
    String category,
	@Schema(example = "1200.99")
    @Digits(integer = 10, fraction = 2, message = "Price must be a number with up to 10 digits and 2 decimal places (ex: 55.99)")
    BigDecimal price,
	@Schema(example = "3")
    Integer quantity,
	@Schema(example = "REF-123-456")
    String internalReference,
	@Schema(example = "15")
    Long shellId,
	@Schema(example = "INSTOCK")
    @Enumerated(EnumType.STRING)
    InventoryStatus inventoryStatus,
	@Schema(example = "4")
    Integer rating
) {}
