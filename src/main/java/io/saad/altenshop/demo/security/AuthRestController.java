package io.saad.altenshop.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.dto.UserDTO;
import io.saad.altenshop.demo.exception.model.UserAlreadyExistException;
import io.saad.altenshop.demo.security.service.TokenService;
import io.saad.altenshop.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class AuthRestController {

	private final TokenService tokenService;
	
    private final UserService userService;
	
	@PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/account")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userDto) {
    	
        if(userService.findByEmail(userDto.getEmail())){
            throw new UserAlreadyExistException();
        }
        return userService.saveUser(userDto);
    }
}
