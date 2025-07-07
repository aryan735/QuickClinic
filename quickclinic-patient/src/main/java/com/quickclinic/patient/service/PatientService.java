package com.quickclinic.patient.service;

import com.quickclinic.patient.controller.dtos.PatientRequestDto;
import com.quickclinic.patient.controller.dtos.PatientResponseDto;

import java.util.List;

public interface PatientService {

    void createPatient(PatientRequestDto patient);

    PatientResponseDto getPatientById(Long id);
    List<PatientResponseDto> getAllPatientsOfAUser(Long userId);
    void updatePatientDetails(PatientRequestDto patient, Long patientId);
    void deletePatientApplication(Long patientId);

}
