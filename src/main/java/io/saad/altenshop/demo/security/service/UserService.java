package io.saad.altenshop.demo.security.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.repository.UserRepository;
import io.saad.altenshop.demo.security.IAuthenticationFacade;
import io.saad.altenshop.demo.security.dto.UserDTO;
import io.saad.altenshop.demo.security.exception.model.AppUserNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	
	private final IAuthenticationFacade authenticationFacade;

    public ResponseEntity<UserDTO> saveUser(UserDTO dto) {
        AppUser entity = new AppUser();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        AppUser savedUser = userRepository.save(entity);
    	
        dto.setPassword("******");
        dto.setUserId(savedUser.getUserId());
        return ResponseEntity.ok(dto);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public Long authenticatedUserId() {
		AppUser authenticatedUser = this.userRepository.findByEmail(this.authenticationFacade.getAuthentication().getName())
				.orElseThrow(AppUserNotFoundException::new);
		return authenticatedUser.getUserId();
    }
    
    public AppUser authenticatedUserEntity() {
		return this.userRepository.findByEmail(this.authenticationFacade.getAuthentication().getName())
				.orElseThrow(AppUserNotFoundException::new);
    }
}
