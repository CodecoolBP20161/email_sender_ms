package com.codecool.unit;

import com.codecool.exception.InvalidEmailException;
import com.codecool.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testRecipientsAcceptsValidMailAddress() throws Exception {
        email.setReceivers(validMail);
        assertTrue(email.getReceivers().contains(validMail));
    }

    @Test(expected = InvalidEmailException.class)
    public void testRecipientsAcceptsOnlyValidMailAddress() throws Exception {
        email.setReceivers(invalidMail);
    }

    @Test
    public void testInvalidEmailNotAddedToReceivers() throws Exception {
        try {
            email.setReceivers(invalidMail);
        } catch (InvalidEmailException ignored) {
        } finally {
            assertTrue(email.getReceivers().isEmpty());
        }
    }
}
