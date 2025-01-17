package io.saad.altenshop.demo.security.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
	
    private Long userId;
    private String username;
    private String firstname;
    private String password;
    
    @Email
    private String email;
}
