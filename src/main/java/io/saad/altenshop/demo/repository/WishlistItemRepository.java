package io.saad.altenshop.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.saad.altenshop.demo.entity.WishlistItem;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long>{

	@Query("SELECT ci FROM WishlistItem ci WHERE ci.appUser.id = :appUserId")
	List<WishlistItem> fetchWishlistItemsOfAppUserById(Long appUserId);
	
	@Query("SELECT ci FROM WishlistItem ci WHERE ci.id = :wishlistItemId AND ci.appUser.id = :appUserId")
	Optional<WishlistItem> fetchWishlistItemByIdAndByAppUserId(Long wishlistItemId, Long appUserId);
}
