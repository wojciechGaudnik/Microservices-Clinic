package com.clinics.patient.client;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.common.DTO.response.outer.VisitRegisterResponseDTO;
import com.clinics.common.security.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PatientClient {
    final private RestTemplate restTemplate;

    public PatientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VisitRegisterResponseDTO registerVisit(UUID patientUUID, VisitDTO visitDTO){

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatientUUID(patientUUID);
        appointmentDTO.setPatientFirstName("Piotr");
        appointmentDTO.setPatientSecondName("Sobczynski");

        String url = String.format("http://doctor-mssc/doctors/%s/calendars/%s/appointments/%s", visitDTO.getDoctorUUID(), visitDTO.getCalendarUUID(), visitDTO.getAppointmentUUID());
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<AppointmentDTO> requestEntity = new HttpEntity<>(appointmentDTO, httpHeaders);
        try {
            ResponseEntity<Void> responseFromAuth = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
        }catch(Exception e){
            System.out.println("ERROR");

        }
           /* patchForObject(String url, @Nullable Object request, Class<T> responseType,
                    Map<String, ?> uriVariables))*/

        return new VisitRegisterResponseDTO();
    }
}
