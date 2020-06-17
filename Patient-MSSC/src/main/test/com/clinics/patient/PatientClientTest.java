package com.clinics.patient;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientClientTest {

    @Mock private RestTemplate restTemplate;
    @Mock private ModelMapper modelMapper;
    @Mock private Patient patient;
    @InjectMocks PatientClient subject;

    //TODO finish those tests
    @Test
    void shouldRegisterVisitHappyCase(){


    }
}
