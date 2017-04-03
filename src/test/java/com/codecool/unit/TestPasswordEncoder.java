package com.codecool.unit;


import com.codecool.service.PasswordEncoderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestPasswordEncoder {

    @Autowired
    private PasswordEncoderService service;

    private String password;
    private String encodedPassword;

    @Before
    public void setup() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        password = "password";
        encodedPassword = service.encrypt(password);
    }

    @Test
    public void testPasswordGettingEncrypted() {
        assertTrue(!encodedPassword.equals(password));
    }

    @Test
    public void testPasswordDecoder() throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        assertTrue(service.decrypt(encodedPassword).equals(password));
    }
}
