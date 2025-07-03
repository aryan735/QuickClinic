package com.quickclinic.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class QuickclinicPatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickclinicPatientApplication.class, args);
	}

}
