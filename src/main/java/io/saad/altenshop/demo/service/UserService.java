package io.saad.altenshop.demo.service;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.UserDTO;
import io.saad.altenshop.demo.entity.Cart;
import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.entity.Wishlist;
import io.saad.altenshop.demo.repository.CartRepository;
import io.saad.altenshop.demo.repository.UserRepository;
import io.saad.altenshop.demo.repository.WishlistRepository;
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
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        User savedUser = userRepository.save(entity);
        
    	Cart userCart = Cart.builder().user(savedUser).build();
    	this.cartRepository.save(userCart);
    	
    	Wishlist userWishlist = Wishlist.builder().user(savedUser).build();
    	this.wishlistRepository.save(userWishlist);
    	
        dto.setPassword("******");
        dto.setUserId(savedUser.getUserId());
        return ResponseEntity.ok(dto);
    }

    public boolean findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return true;
        }
        return false;
    }
}
