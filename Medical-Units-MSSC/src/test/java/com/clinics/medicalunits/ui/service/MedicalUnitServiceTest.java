package com.clinics.medicalunits.ui.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalUnitServiceTest {

	@Test
	void getAll(){
		String result = MedicalUnitService.getAllTest();
		assertEquals("test message", result);

	}
}