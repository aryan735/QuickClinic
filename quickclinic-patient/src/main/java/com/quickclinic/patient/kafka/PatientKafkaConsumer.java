package com.quickclinic.patient.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickclinic.patient.kafka.dto.PatientRegisteredEvent;
import com.quickclinic.patient.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PatientKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @KafkaListener(topics = "quickclinic-test", groupId="noti")
    public void consumePatientEvent(ConsumerRecord<String , String > record){
        try{
            String  value = record.value();
            log.info("Received Kafka Event : {}", value);
            PatientRegisteredEvent event = objectMapper.readValue(value, PatientRegisteredEvent.class);
            emailService.sendApplictionCopy(event);
        } catch (Exception e) {
            log.error("Error processing Kafka message : {}", e.getMessage());
        }
    }

}
