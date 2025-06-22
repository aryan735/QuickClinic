package com.quickclinic.userauth.controller;

import com.quickclinic.userauth.dto.UserRequestDto;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;

    @GetMapping
    public String health(){
        return "Okey";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDto user){
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully!");
    }

}
