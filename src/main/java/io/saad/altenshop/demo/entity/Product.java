package io.saad.altenshop.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable{
	
    private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String image;
    
    @Column(nullable = false)
    private String category;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    private Integer quantity;
    
    private String internalReference;
    
    private Long shellId;
    
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    
    private Integer rating;
    
    @CreatedDate
    private Instant createdAt;
    
    @LastModifiedDate
    private Instant updatedAt;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category)
				&& Objects.equals(code, other.code) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && Objects.equals(internalReference, other.internalReference)
				&& inventoryStatus == other.inventoryStatus && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price) && Objects.equals(quantity, other.quantity)
				&& Objects.equals(rating, other.rating) && Objects.equals(shellId, other.shellId)
				&& Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public int hashCode() {
		return 2025;
	}
}
