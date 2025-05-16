package io.saad.altenshop.demo.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.exception.model.UserAlreadyExistException;
import io.saad.altenshop.demo.security.dto.AuthenticationRequest;
import io.saad.altenshop.demo.security.dto.AuthenticationResponse;
import io.saad.altenshop.demo.security.dto.UserDTO;
import io.saad.altenshop.demo.security.service.TokenService;
import io.saad.altenshop.demo.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class AuthRestController {
	
	private final AuthenticationManager authenticationManager;

	private final TokenService tokenService;
	
    private final UserService userService;
	
	@PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> token(@RequestBody AuthenticationRequest authenticationRequest) {
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password()));
		
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(tokenService.generateToken(authentication));
        
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userDto) {
    	
        if(userService.existsByEmail(userDto.getEmail())) throw new UserAlreadyExistException();
        
        return userService.saveUser(userDto);
    }
}
