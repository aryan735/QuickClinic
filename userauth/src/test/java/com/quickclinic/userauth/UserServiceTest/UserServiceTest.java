package com.quickclinic.userauth.UserServiceTest;

import com.quickclinic.userauth.dto.UserResponseDto;
import com.quickclinic.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class UserServiceTest {
    private final UserService userService;

    @Test
    public void testUserDetails(String username){

    }
}
