package com.quickclinic.userauth.service;

import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.entity.UserModel;
import com.quickclinic.userauth.enums.Roles;
import com.quickclinic.userauth.exception.UserAuthException;
import com.quickclinic.userauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public void grantAdmin(Long id) {
        if (userRepository.existsById(id)){
            UserModel user = userRepository.findById(id).orElseThrow(()->new UserAuthException("User not found with this id :"+id));
            user.getRoles().add(Roles.ADMIN.name());
            log.info("Admin granted with this id : {}",id);
            userRepository.save(user);
            return;
        }
        throw new UserAuthException("User not found while granting admin!");
    }
    public UserResponseDto getUserById(Long id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserAuthException("User not found"));
        return UserResponseDto.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .age(userModel.getAge())
                .build();

    }

    public List<UserResponseDto> getAllUsers() {
        List<UserModel> all = userRepository.findAll();
        if (all.isEmpty()){
            throw new UserAuthException("Users not found while fetching all users by admin!");
        }
        log.info("Users found while fetching all users by admin!");
        return all.stream()
                .map(userModel -> UserResponseDto.builder()
                        .id(userModel.getId())
                        .username(userModel.getUsername())
                        .email(userModel.getEmail())
                        .age(userModel.getAge())
                        .build())
                .toList();

    }
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            log.info("User Deleted Successfully!");
            return;
        }
        log.warn("User Deletion Failed with this id : {}",id);
        throw new UserAuthException("User Deletion Failed!");
    }

}
