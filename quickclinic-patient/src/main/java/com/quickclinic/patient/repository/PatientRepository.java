package com.quickclinic.patient.repository;

import com.quickclinic.patient.controller.dtos.PatientResponseDto;
import com.quickclinic.patient.entity.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository <PatientModel,Long> {
    @Query("SELECT new com.quickclinic.patient.dtos.PatientResponseDto(u.patientId,u.userId,u.doctorId,u.doctorName,u.name,u.email,u.dob,u.age,u.gender,u.phoneNo,u.alternativePhoneNo,u.address,u.city,u.state,u.zip,u.createdTime,u.updatedTime) FROM PatientModel u WHERE u.userId= : userId")
   List<PatientResponseDto>  getPatientsByUserId(@Param("userId") Long userId);

   @Modifying
    @Transactional
    @Query("UPDATE PatientModel p SET p.doctorName= :doctorName, p.doctorId=:doctorId, p.name=:name,p.email=:email,p.dob=:dob,p.age=:age,p.gender=:gender,p.phoneNo=:phoneNo,p.alternativePhoneNo=:altNo,p.address=:address,p.city= :city,p.state= :state,p.zip= :zip WHERE p.patientId = : patientId")
    int updatePatientDetails(@Param("doctorName") String doctorName, @Param("doctorId") Long doctorId, @Param("name") String name, @Param("email") String email, @Param("dob") LocalDate dob, @Param("age") int age, @Param("gender") String gender, @Param("phoneNo") String phoneNo, @Param("altNo") String altNo, @Param("address") String address, @Param("city") String city, @Param("state") String state, @Param("zip") String zip, @Param("patientId") Long patientId);
}
