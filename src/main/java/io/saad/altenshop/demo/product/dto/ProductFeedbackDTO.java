package io.saad.altenshop.demo.product.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductFeedbackDTO {
	
	private Long id;
    
    private String code;
    private String name;
    private String description;
    private String image;
}
