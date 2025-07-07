package com.quickclinic.patient.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionModel {
    private LocalDateTime timestamp;
    private String message;
    private String errorCode;
}
