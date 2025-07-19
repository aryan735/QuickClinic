package com.quickclinic.patient.controller;

import com.quickclinic.patient.client.UserClient;
import com.quickclinic.patient.client.dto.BasicUserInfoDto;
import com.quickclinic.patient.controller.dtos.PatientRequestDto;
import com.quickclinic.patient.controller.dtos.PatientResponseDto;
import com.quickclinic.patient.controller.dtos.PatientUpdateDto;
import com.quickclinic.patient.service.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientServiceImpl patientService;
    private final UserClient userClient;

    @PostMapping("/register/patient")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRequestDto patient){
        patientService.createPatient(patient);
       return ResponseEntity.status(HttpStatus.OK).body("Patient Application Registered!");
    }
    @GetMapping("/get-patient/id/{id}")
    public ResponseEntity<PatientResponseDto> getPatient(@PathVariable Long id){
        PatientResponseDto patientById = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientById);
    }

    @GetMapping("/get-All-patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatientsOfAUser(){
        BasicUserInfoDto userDetails = userClient.getUserDetails();
        List<PatientResponseDto> patientById = patientService.getAllPatientsOfAUser(userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(patientById);
    }
    @PutMapping("/update-patient-details/patientId/{id}")
    public ResponseEntity<String> updatePatient(@RequestBody PatientUpdateDto patientRequestDto, @PathVariable Long id){
        patientService.updatePatientDetails(patientRequestDto,id);
        return ResponseEntity.status(HttpStatus.OK).body("Patient Application updated successfully!");
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        BasicUserInfoDto userDetails = userClient.getUserDetails();
        patientService.deletePatient(id,userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Patient Application deleted successfully!");
    }
}
