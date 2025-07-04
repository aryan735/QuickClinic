package com.quickclinic.patient.service;

import com.quickclinic.patient.kafka.dto.PatientRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        mail.setText(event.getEmail());
        mail.setSubject("✅ QuickClinic Confirmation - Patient ID: " + event.getPatientId());
        mail.setText("Hello " + event.getName() + ",\n\n" +
                "Your application at QuickClinic has been successfully submitted.\n\n" +
                "Here is a copy of your submitted data:\n\n" +
                "Patient ID: " + event.getPatientId() + "\n" +
                "Doctor: " + event.getDoctorName() + "\n" +
                "Name: " + event.getName() + "\n" +
                "Email: " + event.getEmail() + "\n" +
                "DOB: " + event.getDob() + "\n" +
                "Age: " + event.getAge() + "\n" +
                "Gender: " + event.getGender() + "\n" +
                "Phone: " + event.getPhoneNo() + "\n" +
                "Alt Phone: " + event.getAlternativePhoneNo() + "\n" +
                "Address: " + event.getAddress() + ", " + event.getCity() + ", " + event.getState() + " - " + event.getZip() + "\n\n" +
                "Please keep this as proof of your application.\n\n" +
                "Regards,\nQuickClinic Team");
           mailSender.send(mail);
           log.info("Email sent successfully to {} ",event.getEmail());
       }catch (Exception e){
           log.error("Failed to send email to {} - {}",event.getEmail(),e.getMessage());
       }
    }

}
