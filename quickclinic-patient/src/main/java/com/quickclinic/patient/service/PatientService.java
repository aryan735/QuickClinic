package com.quickclinic.patient.service;

import com.quickclinic.patient.dtos.PatientRequestDto;

public interface PatientService {

    int createPatient(PatientRequestDto patient , Long userId);
}
