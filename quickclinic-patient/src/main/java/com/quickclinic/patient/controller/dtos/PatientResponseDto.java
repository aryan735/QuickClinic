package com.quickclinic.patient.controller.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {

    private String status;
    private Long patientId;
    private Long userId;
    private Long doctorId;
    private String doctorName;

    private String name;
    private String email;
    private LocalDate dob;
    private int age;
    private String gender;
    private String phoneNo;
    private String alternativePhoneNo;

    private String address;
    private String city;
    private String state;
    private String zip;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}

