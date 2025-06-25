package com.quickclinic.userauth.controller;


import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.service.AdminService;
import com.quickclinic.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    @PostMapping("/grant-admin/id/{id}")
    public ResponseEntity<String> createAdmin(@PathVariable Long id){
       adminService.grantAdmin(id);
       return ResponseEntity.status(HttpStatus.OK).body("Admin granted Successfully!");
    }

    @GetMapping("/get-user-details/id/{id}")
    public ResponseEntity<UserResponseDto> getUserDetails(@PathVariable Long id){
        UserResponseDto user = adminService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/get-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> users = adminService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @DeleteMapping("/delete-user-by-Id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
               adminService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully!");
    }



}
