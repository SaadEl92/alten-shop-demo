package io.saad.altenshop.demo.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
	private List<CartItem> cartItems;
	
	//###############Utility Methodes (CartItem)########################################
    //##########################################################################################    
    public void addCartItem(CartItem cartItem){
    	this.cartItems.add(cartItem);
    	cartItem.setCart(this);
    }

	public void removeCartItem(CartItem cartItem) {
		cartItem.setCart(null);
    	this.cartItems.remove(cartItem);
    }
    
    public void removeCartItems() {
    	Iterator<CartItem> iterator = this.cartItems.iterator();
    	while (iterator.hasNext()) {
			CartItem cartItem = iterator.next();
			cartItem.setCart(null);
			iterator.remove();
		}
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartItems, other.cartItems) && Objects.equals(id, other.id)
				&& Objects.equals(user, other.user);
	}

	@Override
	public int hashCode() {
		return 2025;
	}
    
    
	
}
