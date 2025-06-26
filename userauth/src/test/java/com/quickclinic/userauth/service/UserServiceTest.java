package com.quickclinic.userauth.service;


import com.quickclinic.userauth.dto.UpdateUserDto;
import com.quickclinic.userauth.dto.UserRequestDto;
import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.entity.UserModel;
import com.quickclinic.userauth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testRegisterUser_Success() {
        String rawPassword = "password123";
        when(encoder.encode(rawPassword)).thenReturn("encodeePass");
        UserRequestDto user = new UserRequestDto();
        user.setUsername("testuser");
        user.setPassword(rawPassword);

        userService.saveUser(user);

        //Assert (Verify intercations)
        verify(encoder).encode(rawPassword);
        verify(userRepository).save(any(UserModel.class));
    }
    @Test
    void testDeleteUser_Success() {


        when(userRepository.existsByUsername("Ravan")).thenReturn(true);
        when(userRepository.deleteByUsername("Ravan")).thenReturn(1);

        String result = userService.deleteUser("Ravan");

        assertEquals("User Deleted Successfully!", result);
        verify(userRepository).existsByUsername("Ravan");
        verify(userRepository).deleteByUsername("Ravan");
    }

    @Test
    public void testFindUserByUsername_Success(){
        UserResponseDto mockDto = new UserResponseDto();
        mockDto.setUsername("Ravan");

        when(userRepository.getUserDetailsByUsername("Ravan")).thenReturn(mockDto);

        UserResponseDto result = userService.getUserByUsername("Ravan");

        assertNotNull(result);
        assertEquals("Ravan", result.getUsername());
        verify(userRepository).getUserDetailsByUsername("Ravan");
    }

    @Test
    public void testUpdateUserDetails(){
        UpdateUserDto user = new UpdateUserDto();
        user.setUsername("Ram");
        user.setEmail("ram@gmail.com");
        user.setAge(21);
        String username="Ravan";
        when(userRepository.existsByUsername(username)).thenReturn(true);
       when(userRepository.updateUserDetails(user.getUsername(),user.getEmail(),user.getAge(),username)).thenReturn(1);

       UserModel userModel1= new UserModel();
       userModel1.setId(1L);
       userModel1.setUsername("Ram");
       userModel1.setEmail("ram@gmail.com");
       userModel1.setAge(21);
        when(userRepository.findByUsername("Ram")).thenReturn(userModel1);

        UserResponseDto user1 = userService.updateUserDetails(user, username);
        assertEquals("Ram",user1.getUsername());
        assertEquals("ram@gmail.com",user1.getEmail());
        assertEquals(21,user1.getAge());
        verify(userRepository).existsByUsername(username);
        verify(userRepository).updateUserDetails("Ram", "ram@gmail.com", 21,username);
        verify(userRepository).findByUsername("Ram");

    }


}
