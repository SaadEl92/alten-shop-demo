package io.saad.altenshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.saad.altenshop.demo.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
