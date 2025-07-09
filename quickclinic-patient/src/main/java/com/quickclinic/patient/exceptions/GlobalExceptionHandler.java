package com.quickclinic.patient.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.concurrent.RecursiveTask;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<ExceptionModel> handlePatientException(PatientException ex, WebRequest request){
        log.error("Patient Exception : {}", ex.getMessage());

        ExceptionModel response = new ExceptionModel(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                "PATIENT_NOT_FOUND"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> handleValidationException(MethodArgumentNotValidException ex, WebRequest request){
        String errMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(err-> err.getField()+ ": "+err.getDefaultMessage())
                .findFirst()
                .orElse("Invalid Input");

        log.error("Validatoin error: {}", errMessage);

        ExceptionModel respone = new ExceptionModel(
                LocalDateTime.now(),
                errMessage,
                request.getDescription(false),
                "VALIDATION_FAILED"

        );
        return new ResponseEntity<>(respone,HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handleGenericException(Exception ex, WebRequest request){
        log.error("Unhandled Exception : {}",ex.getMessage(),ex);

        ExceptionModel response = new ExceptionModel(
                LocalDateTime.now(),
                "Something went wrong, please contact support.",
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
       return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
   }

}
