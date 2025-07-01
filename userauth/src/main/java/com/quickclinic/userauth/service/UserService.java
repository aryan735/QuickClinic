package com.quickclinic.userauth.service;

import com.quickclinic.userauth.dto.UpdateUserDto;
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
    private final PasswordEncoder passwordEncoder;


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

    public void updateUserDetails(UpdateUserDto user, String username) {
        if (userRepository.existsByUsername(username)) {
            UserModel user1 = userRepository.findByUsername(username);
            int i = userRepository.updateUserDetails(user.getUsername(), user.getEmail(), user.getAge(), user1.getId());
            if (i<1){
               throw new UserAuthException("Updataion failed!");
            }
            return;
        }
        log.warn("User not found with this username :{}",username);
        throw new UserAuthException("User not found!");
    }

    public String deleteUser(String username) {
        if (userRepository.existsByUsername(username)){
           int i= userRepository.deleteByUsername(username);
           if (i>0){
               log.info("User Deleted Successfully!");
               return "User Deleted Successfully!";
           }
        }
        log.warn("User Deletion Failed with this username : {}",username);
        throw new UserAuthException("User Deletion Failed!");
    }


}
