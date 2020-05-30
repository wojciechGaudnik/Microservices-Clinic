package com.clinics.doctors;

import com.clinics.doctors.ui.controller.DoctorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DoctorsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private DoctorController doctorController;

	@Test
	void contextLoads() {
		assertThat(doctorController).isNotNull();
	}

	@Test
	public void helloWorld() {
		assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/doctors/test", String.class))
				.contains("Hello world");
	}

}
