package com.quickclinic.patient.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickclinic.patient.kafka.dto.PatientRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    //Kafka object injected
    private final KafkaTemplate<String ,String> kafkaTemplate;

    //Kafka topic
    private static final String TOPIC = "quickclinic-test";

    //this method will send the data to the topic
    public void sendEvent(PatientRegisteredEvent event){
        try {
            String json = new ObjectMapper().writeValueAsString(event);
            kafkaTemplate.send(TOPIC,json);
            log.info("Kafka Event Sent to [{}]: {}",TOPIC,json);

        }catch (Exception e){
            log.error("Failed to send Kafka event: {}", e.getMessage(),e);
        }
    }

}
