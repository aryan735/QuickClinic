package com.quickclinic.userauth.UserServiceTest;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest

public class UserServiceTest {


    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testPwd() {
        String raw = "ramsing123";
        String hashed = encoder.encode(raw);  // Generate hash

        System.out.println("Generated hash: " + hashed);

        boolean matches = encoder.matches(raw, hashed); // Now this will be true
        System.out.println("Password match? " + matches);

    }

}
