package com.clinics.patient;

import com.clinics.common.DTO.request.outer.DiseaseDTO;
import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.entity.Disease;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.exception.RemovalOfFinishedVisitException;
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

import javax.transaction.Transactional;
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

//    @Override
//    @Transactional
//    public void removeDisease(UUID visitUUID, UUID diseaseUUID) {
//        Optional<Visit> visit = visitRepository.findByVisitUUID(visitUUID);
//        Optional<Disease> disease = diseaseRepository.findByDiseaseUUID(diseaseUUID);
//
//        if (visit.isEmpty()) {
//            throw new VisitNotFoundException(visitUUID);
//        }
//        if (visit.get().getStatus().equals(VisitStatus.FINISHED)) {
//            throw new RemovalOfFinishedVisitException(visitUUID);
//        } else {
//            diseaseRepository.deleteDiseaseByDiseaseUUID(diseaseUUID);
//        }
//    }

    @Test
    void shouldRemoveDisease(){
        when(visitRepository.findByVisitUUID(visitUUID)).thenReturn(Optional.of(visit));

        subject.removeDisease(visitUUID, diseaseUUID);

        verify(diseaseRepository, times(1)).deleteDiseaseByDiseaseUUID(diseaseUUID);
    }

    @Test
    void RemovalOfFinishedVisitException(){
        when(visitRepository.findByVisitUUID(visitUUID)).thenReturn(Optional.of(visit));
        when(diseaseRepository.findByDiseaseUUID(diseaseUUID)).thenReturn(Optional.of(disease));
        visit.setStatus(VisitStatus.FINISHED);

        Assertions.assertThrows(RemovalOfFinishedVisitException.class, () -> {
            subject.removeDisease(visitUUID, diseaseUUID);
        });
    }

    @Test
    void shouldThrowVisitNotFoundExceptionRemoveDisease(){
        Assertions.assertThrows(VisitNotFoundException.class, () -> {
            subject.removeDisease(visitUUID, diseaseUUID);
        });
    }
}
