package com.clinics.patient;

import com.clinics.patient.client.PatientClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class PatientClientTest {

    @Mock private RestTemplate restTemplate;

    @InjectMocks PatientClient subject;

    //TODO finish those tests
    @Test
    void shouldRegisterVisitHappyCase(){
        Assertions.assertTrue(true);

    }
}
