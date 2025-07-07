package com.quickclinic.patient.repository;

import com.quickclinic.patient.controller.dtos.PatientResponseDto;
import com.quickclinic.patient.entity.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository <PatientModel,Long> {
    @Query("SELECT new com.quickclinic.patient.dtos.PatientResponseDto(u.patientId,u.userId,u.doctorId,u.doctorName,u.name,u.email,u.dob,u.age,u.gender,u.phoneNo,u.alternativePhoneNo,u.address,u.city,u.state,u.zip,u.createdTime,u.updatedTime) FROM PatientModel u WHERE u.userId= : userId")
   List<PatientResponseDto>  getPatientsByUserId(@Param("userId") Long userId);


}
