package io.saad.altenshop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CartItemDTO {
	
    private Long id;
    private Long productId;
    private Integer quantity;
    
}
