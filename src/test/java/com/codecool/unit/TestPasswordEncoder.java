package com.codecool.unit;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestPasswordEncoder {

    @Autowired
    private PasswordEncoderService service;

    private String validKey;
    private String invalidKey;
    private String password;
    private String encodedPassword;

    @Before
    public void setup() {
        validKey = "validKey";
        invalidKey = "invalidKey";
        password = "password";
        encodedPassword = service.encode(password);
    }

    @Test
    public void testPasswordGettingEncrypted() {
        assertTrue(encodedPassword != password);
    }

    @Test
    public void testPasswordDecoder() {
        assertTrue(service.decode(encodedPassword) == password);
    }
}
