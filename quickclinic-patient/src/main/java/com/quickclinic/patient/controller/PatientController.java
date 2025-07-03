package com.quickclinic.patient.controller;

import com.quickclinic.patient.dtos.PatientRequestDto;
import com.quickclinic.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

//    public ResponseEntity<String> registerPatient(@RequestBody PatientRequestDto patient){
//        patientService.patientService()
//    }

}
