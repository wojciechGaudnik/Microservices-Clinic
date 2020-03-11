package com.clinics.patient.client;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.common.DTO.response.outer.VisitRegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatientClient {
    final private RestTemplate restTemplate;

    @Autowired
    public PatientClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VisitRegisterResponseDTO registerVisit(VisitDTO visitDTO){
        //TODO uderzanie do doctora - zwrotka true or false
        //TODO return restTemplate.postForObject("http://doctor-mssc/doctors/{UUID_doctor}/calendars/{UUID_calendar}/appointments}{UUID}/calendar/uuidCal/registerVisit/", registerVisitDTO, VisitRegisterResponseDTO.class);

        //DOCTOR POST http://doctor-mssc/doctors/{UUID_doctor}/calendars/{UUID_calendar}/appointments (w body ??) doktor ustala woly termin z front-endu

        //PATIENT z serwisu z patient-client PATCH/PUT http://doctor-mssc/doctors/{UUID_doctor}/calendars/{UUID_calendar}/appointments/{UUID_appointment} (W body nulle i UUID-patient)
        return new VisitRegisterResponseDTO();
    }
}
