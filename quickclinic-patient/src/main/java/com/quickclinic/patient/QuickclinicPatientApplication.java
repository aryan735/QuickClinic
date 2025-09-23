package com.quickclinic.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients(basePackages = "com.quickclinic.patient.client")
public class QuickclinicPatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickclinicPatientApplication.class, args);
	}

}
