package com.codecool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailSenderApplicationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void testRootTemplate() {
		String body = testRestTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("OK");
	}

}
