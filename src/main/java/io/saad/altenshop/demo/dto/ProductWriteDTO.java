package io.saad.altenshop.demo.dto;

import java.math.BigDecimal;

import io.saad.altenshop.demo.entity.InventoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductWriteDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "f230fh0g3")
    private String code;

    @NotBlank(message = "Product name can't be blank")
    @Schema(example = "Samsung TV")
    private String name;

    @Schema(example = "OLED 4K")
    private String description;

    @Schema(example = "image.jpg")
    private String image;

    @Schema(example = "TV & Monitors")
    private String category;

    @Schema(example = "1200.99")
    @Digits(integer = 10, fraction = 2, message = "Price must be a number with up to 10 digits and 2 decimal places (ex: 55.99)")
    private BigDecimal price;

    @Schema(example = "3")
    private Integer quantity;

    @Schema(example = "REF-123-456")
    private String internalReference;

    @Schema(example = "15")
    private Long shellId;

    @Schema(example = "INSTOCK")
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    @Schema(example = "4")
    private Integer rating;
}

