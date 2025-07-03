package com.quickclinic.patient.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientUpdateDto {

    @Size(min = 3, max = 50, message = "Doctor name must be 3 to 50 characters")
    private String doctorName;

    @Size(min = 3, max = 50, message = "Name must be 3 to 50 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private LocalDate dob;

    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age seems too high")
    private Integer age;

    @Pattern(regexp = "Male|Female|Others", message = "Gender must be Male | Female | Others")
    private String gender;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNo;

    @Pattern(regexp = "^\\d{10}$", message = "Alternative phone must be exactly 10 digits")
    private String alternativePhoneNo;

    private String address;

    private String city;

    private String state;

    @Pattern(regexp = "^\\d{6}$", message = "ZIP must be exactly 6 digits")
    private String zip;
}
