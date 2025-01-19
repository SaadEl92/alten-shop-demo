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
public class Wishlist implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wishlist", orphanRemoval = true)
	private List<WishlistItem> wishlistItems;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Wishlist other = (Wishlist) obj;
		return Objects.equals(id, other.id) && Objects.equals(user, other.user)
				&& Objects.equals(wishlistItems, other.wishlistItems);
	}

	@Override
	public int hashCode() {
		return 2025;
	}
    
	//###############Utility Methodes (WishlistItem)########################################
    //##########################################################################################    
    public void addWishlistItem(WishlistItem wishlistItem){
    	this.wishlistItems.add(wishlistItem);
    	wishlistItem.setWishlist(this);
    }

	public void removeWishlistItem(WishlistItem wishlistItem) {
		wishlistItem.setWishlist(null);
    	this.wishlistItems.remove(wishlistItem);
    }
    
    public void removeWishlistItems() {
    	Iterator<WishlistItem> iterator = this.wishlistItems.iterator();
    	while (iterator.hasNext()) {
			WishlistItem wishlistItem = iterator.next();
			wishlistItem.setWishlist(null);
			iterator.remove();
		}
    }
    
}
