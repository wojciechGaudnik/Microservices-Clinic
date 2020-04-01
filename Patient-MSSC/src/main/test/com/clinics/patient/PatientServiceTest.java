package com.clinics.patient;

import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

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

        verify(patientRepository, times(1)).findByPatientUUID(patientUUID);
        Assertions.assertEquals(patient, result);
    }

    @Test
    void findByUuidShouldThrowWhenNoPatient () {
        Assertions.assertThrows(PatientNotFoundException.class, () -> {
            subject.findByUuid(patientUUID);
        });
    }

    @Test
    void shouldFindAllPatientsVisits(){
        Visit visit = mock(Visit.class);
        when(patientRepository.findByPatientUUID(patientUUID)).thenReturn(Optional.of(patient));
        when(patient.getVisits()).thenReturn(List.of(visit));

        List<Visit> result = subject.findAllVisits(patientUUID);
        Assertions.assertTrue(result.contains(visit));
    }

    @Test
    void shouldThrowPatientNotFoundException(){
        Assertions.assertThrows(PatientNotFoundException.class, () -> {
            subject.findAllVisits(patientUUID);
        });
    }

    @Test
    void shouldActivatePatientInAuth(){
        RegisterPatientDTO registerPatientDTO = mock(RegisterPatientDTO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        subject.addPatient(registerPatientDTO, request);

        verify(patientClient, times(1)).activatePatientInAuth(registerPatientDTO, request);
    }

    @Test
    void shouldSavePatient() {
        RegisterPatientDTO registerPatientDTO = mock(RegisterPatientDTO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(modelMapper.map(registerPatientDTO, Patient.class)).thenReturn(patient);

        subject.addPatient(registerPatientDTO, request);

        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void shouldReturnPatientRegisterResponseDTO(){
        RegisterPatientDTO registerPatientDTO = mock(RegisterPatientDTO.class);
        PatientRegisterResponseDTO patientRegisterResponseDTO = mock(PatientRegisterResponseDTO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(modelMapper.map(registerPatientDTO, Patient.class)).thenReturn(patient);
        when(modelMapper.map(patient, PatientRegisterResponseDTO.class)).thenReturn(patientRegisterResponseDTO);

        PatientRegisterResponseDTO result = subject.addPatient(registerPatientDTO, request);

        Assertions.assertEquals(patientRegisterResponseDTO, result);
    }

    @Test
    void shouldThrowNoSuchElementException(){
        RegisterPatientDTO registerPatientDTO = mock(RegisterPatientDTO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        doThrow(NoSuchElementException.class).when(patientClient).activatePatientInAuth(registerPatientDTO, request);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            subject.addPatient(registerPatientDTO, request);
        });
    }
}
