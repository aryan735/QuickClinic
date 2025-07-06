package com.quickclinic.patient.service;

import com.quickclinic.patient.kafka.dto.PatientRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendApplictionCopy(PatientRegisteredEvent event){
       try {
           SimpleMailMessage mail = new SimpleMailMessage();
           String fullAddress = String.join(", ",
                   StringUtils.defaultIfEmpty(event.getAddress(), ", "),
                   StringUtils.defaultIfEmpty(event.getCity(), ", "),
                   StringUtils.defaultIfEmpty(event.getState(), ", "))
                   + " - " + StringUtils.defaultIfEmpty(event.getZip(), ", ");
        mail.setTo(event.getEmail());
        mail.setSubject("✅ QuickClinic Confirmation - Patient ID: " + event.getPatientId());
           mail.setText("Hello " + StringUtils.defaultIfEmpty(event.getName(), "Patient") + ",\n\n" +
                   "Your application at QuickClinic has been successfully submitted.\n\n" +
                   "Here is a copy of your submitted data:\n\n" +
                   "Status: " + StringUtils.defaultIfEmpty(event.getStatus(), "PENDING") + "\n" +
                   "User ID: " + event.getUserId() + "\n" +
                   "Patient ID: " + event.getPatientId() + "\n" +
                   "Doctor: " + StringUtils.defaultIfEmpty(event.getDoctorName(), "Doctor") + "\n" +
                   "Name: " + StringUtils.defaultIfEmpty(event.getName(), "Patient") + "\n" +
                   "Email: " + StringUtils.defaultIfEmpty(event.getEmail(), "N/A") + "\n" +
                   "DOB: " + (event.getDob() != null ? event.getDob().toString() : "N/A") + "\n" +
                   "Age: " + event.getAge() + "\n" +
                   "Gender: " + StringUtils.defaultIfEmpty(event.getGender(), "N/A") + "\n" +
                   "Phone: " + StringUtils.defaultIfEmpty(event.getPhoneNo(), "N/A") + "\n" +
                   "Alt Phone: " + StringUtils.defaultIfEmpty(event.getAlternativePhoneNo(), "N/A") + "\n" +
                   "Address: " + fullAddress + "\n\n" +
                   "Please keep this as proof of your application.\n\n" +
                   "Regards,\nQuickClinic Team");
           mailSender.send(mail);
           log.info("Email sent successfully to {} ",event.getEmail());
       }catch (Exception e){
           log.error("Failed to send email to {} - {}",event.getEmail(),e.getMessage());
       }
    }

}
