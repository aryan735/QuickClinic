package com.quickclinic.patient.service;

import com.quickclinic.patient.controller.dtos.PatientRequestDto;
import com.quickclinic.patient.controller.dtos.PatientResponseDto;
import com.quickclinic.patient.controller.dtos.PatientUpdateDto;
import com.quickclinic.patient.entity.PatientModel;
import com.quickclinic.patient.exceptions.PatientException;
import com.quickclinic.patient.kafka.KafkaProducer;
import com.quickclinic.patient.kafka.dto.PatientRegisteredEvent;
import com.quickclinic.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;
    private final KafkaProducer kafkaProducer;


    //Saving the patient application
    @Override
    public void createPatient(PatientRequestDto patient) {

            //Creating an instance of the  original PatientModel for saving in db
            PatientModel patientModel = PatientModel.builder()
                    .status("PENDING")
                    .userId(patient.getUserId())
                    .doctorId(patient.getDoctorId())
                    .doctorName(patient.getDoctorName())
                    .name(patient.getName())
                    .email(patient.getEmail())
                    .dob(patient.getDob())
                    .age(patient.getAge())
                    .gender(patient.getGender())
                    .phoneNo(patient.getPhoneNo())
                    .alternativePhoneNo(patient.getAlternativePhoneNo())
                    .address(patient.getAddress())
                    .city(patient.getCity())
                    .state(patient.getState())
                    .zip(patient.getZip())
                    .build();

            //Saving the patient application into database
            PatientModel save = patientRepository.save(patientModel);

            //creating a copy of the application to send to the kafka producer
            PatientRegisteredEvent mailDto = PatientRegisteredEvent.builder()
                    .status(save.getStatus())
                    .userId(save.getUserId())
                    .patientId(save.getPatientId())
                    .doctorName(save.getDoctorName())
                    .name(save.getName())
                    .email(save.getEmail())
                    .dob(save.getDob())
                    .age(save.getAge())
                    .gender(save.getGender())
                    .phoneNo(save.getPhoneNo())
                    .alternativePhoneNo(save.getAlternativePhoneNo())
                    .address(save.getAddress())
                    .city(save.getCity())
                    .state(save.getState())
                    .zip(save.getZip())
                    .build();
            kafkaProducer.sendEvent(mailDto); //sending the copy to the producer
            //logs for debbuging
            log.info("Patient application saved for userId={} and email={}", save.getUserId(), save.getEmail());

    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
            PatientModel patient = patientRepository.findById(id)
                    .orElseThrow(() -> new PatientException("Patient Not Found with this patientId : " + id));

            log.info("Returning the patient info by the patientID : {}",id);
            return PatientResponseDto.builder()
                    .status(patient.getStatus())
                    .patientId(patient.getPatientId())
                    .userId(patient.getUserId())
                    .doctorId(patient.getDoctorId())
                    .doctorName(patient.getDoctorName())
                    .name(patient.getName())
                    .email(patient.getEmail())
                    .dob(patient.getDob())
                    .age(patient.getAge())
                    .gender(patient.getGender())
                    .phoneNo(patient.getPhoneNo())
                    .alternativePhoneNo(patient.getAlternativePhoneNo())
                    .address(patient.getAddress())
                    .city(patient.getCity())
                    .state(patient.getState())
                    .zip(patient.getZip())
                    .build();

    }

    //Fetching all patients applications of a user
    @Override
    public List<PatientResponseDto> getAllPatientsOfAUser(Long userId) {
        List<PatientResponseDto> patientsByUserId = patientRepository.getPatientsByUserId(userId);
        if (patientsByUserId.isEmpty()){
            log.error("Patients are not available with this userId : {}",userId);
            throw new PatientException("Patients not found with this userId : "+userId);
        }
        log.info("Patients Found of this user whose userId is : {}", userId);
        return patientsByUserId;
    }

    @Override
    public void updatePatientDetails(PatientUpdateDto patient, Long patientId) {
        if (patientRepository.existsById(patientId)) {
            int i = patientRepository.updatePatientDetails(patient.getDoctorName(), patient.getDoctorId(), patient.getName(), patient.getEmail(), patient.getDob(), patient.getAge(), patient.getGender(), patient.getPhoneNo(), patient.getAlternativePhoneNo(), patient.getAddress(), patient.getCity(), patient.getState(), patient.getZip(), patientId);
            if (i <1) {
                throw new PatientException("Patient details update failed!");
            }
            log.info("Patient details are updated successfully with the patientId : {}", patientId);
            return;
        }
        log.warn("Patient Not found with this patientId : {}",patientId);
        throw new PatientException("Patient Not found with this patientId : "+patientId);

    }

    @Override
    public void deletePatientApplication(Long patientId) {

    }
}
