package com.codecool.unit;


import com.codecool.service.PasswordEncoderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestPasswordEncoder {

    @InjectMocks
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
