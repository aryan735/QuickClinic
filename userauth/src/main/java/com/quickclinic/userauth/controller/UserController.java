package com.quickclinic.userauth.controller;

import com.quickclinic.userauth.dto.UpdateUserDto;
import com.quickclinic.userauth.dto.UserRequestDto;
import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get-details")
    public ResponseEntity<UserResponseDto> getUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserResponseDto user = userService.getUserByUsername(auth.getName());
        if (user==null){
            throw new UserAuthException("User Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PutMapping("/update-details")
    public ResponseEntity<String> updateUserDetails(@RequestBody UpdateUserDto user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userService.updateUserDetails(user, auth.getName());
        return ResponseEntity.status(HttpStatus.OK).body("User Updated Successfully!");
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String message= userService.deleteUser(auth.getName());
       return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
