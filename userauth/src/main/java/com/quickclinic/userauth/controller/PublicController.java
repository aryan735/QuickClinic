package com.quickclinic.userauth.controller;

import com.quickclinic.userauth.dto.LoginDto;
import com.quickclinic.userauth.dto.UserRequestDto;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.jwt.JwtUtil;
import com.quickclinic.userauth.service.UserDetailsServiceImpl;
import com.quickclinic.userauth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public String health(){
        return "Okey";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRequestDto user){
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
      String jwt = jwtUtil.generateToken(userDetails);

      return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
    }

}
