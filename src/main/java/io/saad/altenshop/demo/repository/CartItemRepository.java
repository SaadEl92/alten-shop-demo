package io.saad.altenshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.saad.altenshop.demo.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	@Query("SELECT ci FROM CartItem ci WHERE ci.appUser.id = :appUserId")
	List<CartItem> fetchCartItemsOfAppUserById(Long appUserId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.id = :cartItemId AND ci.appUser.id = :appUserId")
	Optional<CartItem> fetchCartItemByIdAndByAppUserId(Long cartItemId, Long appUserId);
	
}
