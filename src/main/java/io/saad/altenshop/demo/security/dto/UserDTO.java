package io.saad.altenshop.demo.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
	
    @NotBlank
    @Schema(example = "appuser")
    private String username;
    @NotBlank

    @Schema(example = "appuser firstname")
    private String firstname;
    @NotBlank

    @Schema(example = "123456")
    private String password;
    
    @Email
    @Schema(example = "appuser@user.com")
    private String email;
}
