package com.clinics.patient;

import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.exception.PatientNotFoundException;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.service.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PatientClient patientClient;

    @Mock
    private Patient patient;

    private UUID patientUUID = UUID.randomUUID();

    @InjectMocks PatientServiceImpl subject;

    @Test
    void findByUuidShouldReturnPatient () {
        when(patientRepository.findByPatientUUID(patientUUID)).thenReturn(Optional.of(patient));

        Patient result = subject.findByUuid(patientUUID);
        Assertions.assertEquals(patient, result);
    }

    @Test
    void findByUuidShouldThrowWhenNoPatient () {
        Assertions.assertThrows(PatientNotFoundException.class, () -> {
            subject.findByUuid(patientUUID);
        });
    }
}