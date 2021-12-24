package com.sharfine.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringsecurityApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123456");
        System.out.println("encode = " + encode);
    }

}
