package com.quickclinic.userauth.service;

import com.quickclinic.userauth.dto.UserRequestDto;
import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.entity.UserModel;
import com.quickclinic.userauth.enums.Roles;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void  saveUser(UserRequestDto user) {
        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserAuthException("Username already exists!");
        }
            UserModel userModel = UserModel.builder()
                            .username(user.getUsername())
                                    .password(passwordEncoder.encode(user.getPassword()))
                                            .email(user.getEmail())
                                                    .age(user.getAge())
                    .roles(List.of(Roles.USER.name()))
                                                            .build();
              userRepository.save(userModel);
              log.info("User created Successfully : {}",userModel.getUsername());

    }

    public UserResponseDto getUserByUsername(String username) {
            UserResponseDto user = userRepository.getUserDetailsByUsername(username);
                      if (user==null){
                          throw new UserAuthException("User Not Found!");
                      }
        log.info("User found: {}", user.getUsername());
        return user;

    }
}
