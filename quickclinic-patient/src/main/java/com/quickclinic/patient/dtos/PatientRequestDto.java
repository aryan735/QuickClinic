package com.quickclinic.patient.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long doctorId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String doctorName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate dob;

    @Min(0)
    @Max(150)
    private int age;

    @NotBlank
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank
    @Pattern(regexp = "\\d", message = "Phone number must be 10 digits")
    private String phoneNo;

    @Pattern(regexp = "\\d", message = "Alt phone must be 10 digits")
    private String alternativePhoneNo;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    @Pattern(regexp = "\\d", message = "ZIP must be 6 digits")
    private String zip;
}
