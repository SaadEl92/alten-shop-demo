package io.saad.altenshop.demo.security;

import lombok.AllArgsConstructor;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.saad.altenshop.demo.dto.UserDTO;
import io.saad.altenshop.demo.security.exception.InvalidPayloadException;
import io.saad.altenshop.demo.security.exception.UserIdAlreadyExistException;
import io.saad.altenshop.demo.service.UserService;

@RestController
@RequestMapping("/api/v0/auth")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/account")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto) {
        if (Objects.isNull(userDto)) {
            throw new InvalidPayloadException("Payload cannot be Null");
        }
        if(userService.findByEmail(userDto.getEmail())){
            throw new UserIdAlreadyExistException("Email is already in use");
        }
        return userService.saveUser(userDto);
    }
}