package com.quickclinic.patient.service;

import com.quickclinic.patient.controller.dtos.PatientRequestDto;
import com.quickclinic.patient.controller.dtos.PatientResponseDto;

public interface PatientService {

    void createPatient(PatientRequestDto patient);

    void getPatientById(Long id);
    void getAllPatientsOfAUser(Long userId);
    void UpdatePatientDetails(PatientResponseDto patient, Long patientId);
    void deletePatientApplication(Long patientId);
}
