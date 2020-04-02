package com.clinics.patient.client;

import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.common.DTO.response.outer.VisitRegisterResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.patient.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class PatientClient {
    final private RestTemplate restTemplate;

    public PatientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerVisit(Patient patient, VisitDTO visitDTO){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatientUUID(patient.getPatientUUID());
        appointmentDTO.setPatientFirstName(patient.getFirstName());
        appointmentDTO.setPatientSecondName(patient.getLastName());

        String url = String.format("http://doctor-mssc/doctors/%s/calendars/%s/appointments/%s", visitDTO.getDoctorUUID(), visitDTO.getCalendarUUID(), visitDTO.getAppointmentUUID());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<AppointmentDTO> requestEntity = new HttpEntity<>(appointmentDTO, httpHeaders);
        restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
    }

    public void activatePatientInAuth(RegisterPatientDTO registerPatientDTO, HttpServletRequest request){
        String url = String.format("http://auth/auth/users/%s", registerPatientDTO.getPatientUUID());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtProperties.AUTHORIZATION_HEADER, request.getHeader(JwtProperties.AUTHORIZATION_HEADER));
        HttpEntity<String> requestEntity = new HttpEntity<>("Empty Request", httpHeaders);
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
    }

    public void cancelVisit(Patient patient){

    }
}
