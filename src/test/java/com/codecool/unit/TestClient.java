package com.codecool.unit;

import com.codecool.model.Client;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestClient {

    private Client client;
    private String password;

    @Before
    public void setup() {
        client = new Client();
        password = "password";
    }

    @Test
    public void testRandomUUIDGenerated() {
        assertTrue(client.getApiKey() != null);
    }

    @Test
    public void testPasswordGettingEncrypted() {
        client.setEmailPassword(password);
        assertTrue(client.getEmailPassword() != password);
    }
}
