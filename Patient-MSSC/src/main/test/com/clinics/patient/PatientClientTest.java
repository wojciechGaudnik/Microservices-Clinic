package com.clinics.patient;

import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Disease;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.exception.DiseaseNotFoundException;
import com.clinics.patient.exception.VisitNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientClientTest {

    @Mock private RestTemplate restTemplate;

    @InjectMocks PatientClient subject;

//    public void registerVisit(Patient patient, VisitDTO visitDTO){
//        AppointmentDTO appointmentDTO = new AppointmentDTO();
//        appointmentDTO.setPatientUUID(patient.getPatientUUID());
//        appointmentDTO.setPatientFirstName(patient.getFirstName());
//        appointmentDTO.setPatientSecondName(patient.getLastName());
//
//        String url = String.format("http://doctor-mssc/doctors/%s/calendars/%s/appointments/%s", visitDTO.getDoctorUUID(), visitDTO.getCalendarUUID(), visitDTO.getAppointmentUUID());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        HttpEntity<AppointmentDTO> requestEntity = new HttpEntity<>(appointmentDTO, httpHeaders);
//        restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
//    }

    @Test
    void shouldRegisterVisitHappyCase(){
        AppointmentDTO appointmentDTO = new AppointmentDTO();

    }
}
