package com.quickclinic.userauth.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotNull(message = "Age is required!")
    @Min(value = 1, message = "Age must be at least 1")
    private int age;
}
