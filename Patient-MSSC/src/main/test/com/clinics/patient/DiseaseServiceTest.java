package com.clinics.patient;

import com.clinics.common.DTO.request.outer.DiseaseDTO;
import com.clinics.patient.entity.Disease;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.DiseaseNotFoundException;
import com.clinics.patient.exception.VisitNotFoundException;
import com.clinics.patient.repository.DiseaseRepository;
import com.clinics.patient.repository.VisitRepository;
import com.clinics.patient.service.DiseaseServiceImpl;
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
class DiseaseServiceTest {

    @Mock private ModelMapper modelMapper;

    @Mock private Disease disease;

    @Mock private DiseaseDTO diseaseDTO;

    @Mock private Visit visit;

    @Mock private VisitRepository visitRepository;

    @Mock private DiseaseRepository diseaseRepository;

    private UUID visitUUID = UUID.randomUUID();
    private UUID diseaseUUID = UUID.randomUUID();

    @InjectMocks DiseaseServiceImpl subject;


    @Test
    void shouldReturnVisit(){
        when(visitRepository.findByVisitUUID(visitUUID)).thenReturn(Optional.of(visit));
        when(modelMapper.map(diseaseDTO, Disease.class)).thenReturn(disease);

        subject.addDisease(visitUUID, diseaseDTO);

        verify(diseaseRepository, times(1)).save(disease);
    }

    @Test
    void shouldThrowVisitNotFoundException(){
        Assertions.assertThrows(VisitNotFoundException.class, () -> {
            subject.addDisease(visitUUID, diseaseDTO);
        });
    }

    @Test
    void shouldRemoveDisease(){
        when(diseaseRepository.findByDiseaseUUID(diseaseUUID)).thenReturn(Optional.of(disease));

        subject.removeDisease(diseaseUUID);

        verify(diseaseRepository, times(1)).deleteDiseaseByDiseaseUUID(diseaseUUID);
    }

    @Test
    void shouldThrowDiseaseNotFoundExceptionRemoveDisease(){
        Assertions.assertThrows(DiseaseNotFoundException.class, () -> {
            subject.removeDisease(diseaseUUID);
        });
    }
}
