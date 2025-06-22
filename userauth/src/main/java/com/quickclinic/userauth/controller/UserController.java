package com.quickclinic.userauth.controller;

import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String username){
        UserResponseDto user = userService.getUserByUsername(username);
        if (user==null){
            throw new UserAuthException("User Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
