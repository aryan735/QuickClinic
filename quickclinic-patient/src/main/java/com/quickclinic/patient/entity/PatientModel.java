package com.quickclinic.patient.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Builder
@Data
public class PatientModel {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    // application status
    private String status;
    //patient attachments
    private Long userId;
    private Long doctorId;
    private String doctorName;

    //patient details
    private String name;
    private String email;

    private LocalDate dob;
    private int age;

    private String gender;
    private String phoneNo;
    private String alternativePhoneNo;

    //Location
    private String address;
    private String city;
    private String state;
    private String zip;


    @CreationTimestamp
    private LocalDateTime createdTime;


    @UpdateTimestamp
    private LocalDateTime updatedTime;

}
