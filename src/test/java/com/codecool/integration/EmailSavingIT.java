package com.codecool.integration;

import com.codecool.repository.EmailRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSavingIT {

    @MockBean
    EmailRepository emailRepository;


}
