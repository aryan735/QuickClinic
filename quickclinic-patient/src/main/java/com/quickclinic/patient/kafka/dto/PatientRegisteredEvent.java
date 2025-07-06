package com.quickclinic.patient.kafka.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisteredEvent {

    private String status;
    private Long userId;
    private Long patientId;
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
}