package org.thoughtworks.lean.identity.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImplTest {

    @Test
    public void shoud_create_encode_passed() {

        String encode = new BCryptPasswordEncoder().encode("user");
        System.out.println(encode);
    }

}