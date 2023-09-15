package com.inventory.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationBody {
    @NotNull
    @NotBlank
   @Size(min = 4,max = 255)
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Size(max = 60,min = 5)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
}
