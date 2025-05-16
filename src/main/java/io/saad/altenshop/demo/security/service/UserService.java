package io.saad.altenshop.demo.security.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.entity.Cart;
import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.entity.Wishlist;
import io.saad.altenshop.demo.repository.CartRepository;
import io.saad.altenshop.demo.repository.UserRepository;
import io.saad.altenshop.demo.repository.WishlistRepository;
import io.saad.altenshop.demo.security.dto.UserDTO;
import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
	
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final WishlistRepository wishlistRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDTO> saveUser(UserDTO dto) {
        AppUser entity = new AppUser();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        AppUser savedUser = userRepository.save(entity);
        
    	Cart userCart = Cart.builder().appUser(savedUser).build();
    	this.cartRepository.save(userCart);
    	
    	Wishlist userWishlist = Wishlist.builder().appUser(savedUser).build();
    	this.wishlistRepository.save(userWishlist);
    	
        dto.setPassword("******");
        dto.setUserId(savedUser.getUserId());
        return ResponseEntity.ok(dto);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
