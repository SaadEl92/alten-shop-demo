package io.saad.altenshop.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String password;
    
    @Email
    private String email;
}
