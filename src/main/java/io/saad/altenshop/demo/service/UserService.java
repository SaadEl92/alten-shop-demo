package io.saad.altenshop.demo.service;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.dto.UserDTO;
import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserDTO> saveUser(UserDTO dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        User savedUser = userRepository.save(entity);
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
