package com.quickclinic.patient.service;

import com.quickclinic.patient.controller.dtos.PatientRequestDto;
import com.quickclinic.patient.controller.dtos.PatientResponseDto;
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
        try {
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
        }catch (Exception e){
            log.error("Exception While saving user!");
            throw new PatientException("Exception while saving patient application!");
        }

    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
        PatientModel patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Patient Not Found with this patientId : " + id));

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

    @Override
    public List<PatientResponseDto> getAllPatientsOfAUser(Long userId) {
return null;
    }

    @Override
    public void updatePatientDetails(PatientRequestDto patient, Long patientId) {

    }

    @Override
    public void deletePatientApplication(Long patientId) {

    }
}
