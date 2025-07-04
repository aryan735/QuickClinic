package com.quickclinic.patient.service;

import com.quickclinic.patient.controller.dtos.PatientRequestDto;

public interface PatientService {

    int createPatient(PatientRequestDto patient , Long userId);
}
