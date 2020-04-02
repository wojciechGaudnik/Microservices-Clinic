package com.clinics.patient;

import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.VisitNotFoundException;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import com.clinics.patient.service.VisitServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceTest {

    @Mock private PatientRepository patientRepository;

    @Mock private ModelMapper modelMapper;

    @Mock private PatientClient patientClient;

    @Mock private Visit visit;

    @Mock private VisitRepository visitRepository;

    private UUID visitUUID = UUID.randomUUID();

    @InjectMocks VisitServiceImpl subject;

    @Test
    void shouldReturnVisitFindByUUID () {
        when(visitRepository.findByVisitUUID(visitUUID)).thenReturn(Optional.of(visit));

        Visit result = subject.findByUuid(visitUUID);

        verify(visitRepository, times(1)).findByVisitUUID(visitUUID);
        Assertions.assertEquals(visit, result);
    }

    @Test
    void findByUuidShouldThrowVisitNotFoundException () {
        Assertions.assertThrows(VisitNotFoundException.class, () -> {
            subject.findByUuid(visitUUID);
        });
    }

}
