package com.codecool.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerStatusIsOkIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIndexPage() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertTrue(body.equals("OK"));
    }
}
