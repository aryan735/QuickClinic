package com.quickclinic.userauth.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    private String username;
    @Size(min = 6)
    private String password;
    @NotBlank
    private String email;
    @NotNull(message = "Age is required!")
    @Min(value = 1, message = "Age must be at least 1")
    private int age;
}
