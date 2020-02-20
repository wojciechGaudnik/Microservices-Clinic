package com.clinics.patient.client;


import com.clinics.common.DTO.request.VisitDTO;
import com.clinics.common.DTO.response.VisitRegisterResponseDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatientClient {

    @Autowired
    RestTemplate restTemplate;

    public VisitRegisterResponseDTO registerVisit(VisitDTO visitDTO){
        //return restTemplate.postForObject("http://doctor/registerVisit/", registerVisitDTO, VisitRegisterResponseDTO.class);
        return new VisitRegisterResponseDTO();
    }

    public Boolean activatePatientInAuth(Patient patient){ //really boolean, maybe sth else ?
        //TODO map patient to to SetUserActiveDTO
        //TODO call auth with SetUserActiveDTO, handle exception
        return true;
    }
}
