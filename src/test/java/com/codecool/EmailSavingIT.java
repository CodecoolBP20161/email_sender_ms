package com.codecool;

import com.codecool.exception.InvalidEmailException;
import com.codecool.model.Client;
import com.codecool.model.Email;
import com.codecool.repository.ClientRepository;
import com.codecool.repository.EmailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class EmailSavingIT {

    private Email email;
    private Client client = new Client();
    private static final String body = "body";
    private static final String subject = "subject";
    private static final String receiver = "test@test.com";

    private Validator validator;
    private Set<ConstraintViolation<Email>> violations;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    ClientRepository clientRepository;

    @Before
    public void setup() throws InvalidEmailException {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

        emailRepository.deleteAll();
        clientRepository.deleteAll();
        client = clientRepository.save(client);
        email = new Email();
        email.setBody(body);
        email.setSubject(subject);
        email.setReceivers(receiver);
    }

    @Test
    public void testDataIsInsertedIfValidationIsOk() throws Exception {
        email.setClient(client);
        email = emailRepository.save(email);
        assertEquals(1, emailRepository.findAll().size());
    }

    @Test
    public void testIfBodyNullNotSavingIntoDb() throws Exception {
        email.setClient(client);
        email.setBody(null);
        violations = this.validator.validate(email);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testIfSubjectNullNotSavingIntoDb() throws Exception {
        email.setClient(client);
        email.setSubject(null);
        violations = this.validator.validate(email);
        assertFalse(violations.isEmpty());
    }

    @Test(expected = InvalidEmailException.class)
    public void testIfReceiversNullNotSavingIntoDb() throws Exception {
        email.setClient(client);
        email.setReceivers(null);
    }

    @Test
    public void testIfNoClientNotSavingIntoDb() throws Exception {
        violations = this.validator.validate(email);
        assertFalse(violations.isEmpty());
    }
}
