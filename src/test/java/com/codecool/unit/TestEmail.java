package com.codecool.unit;

import com.codecool.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestEmail {

    Email email;
    String validMail;
    String invalidMail;

    @Before
    public void setup() {
        email = new Email();
        validMail = "carbalage@gmail.com";
        invalidMail = "invalidmail";
    }

    @Test(expected = Exception.class)
    public void testReciepntsAcceptsOnlyValidmailAddress() {
        email.setReceivers(invalidMail);
    }
}
