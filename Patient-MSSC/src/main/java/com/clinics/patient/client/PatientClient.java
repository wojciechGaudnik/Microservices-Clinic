package com.clinics.patient.client;


import com.clinics.common.DTO.request.RegisterVisitDTO;
import com.clinics.common.DTO.response.VisitRegisterResponseDTO;
import com.clinics.patient.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatientClient {

    @Autowired
    RestTemplate restTemplate;

    public VisitRegisterResponseDTO registerVisit(Visit visit){
        RegisterVisitDTO registerVisitDTO = new RegisterVisitDTO();
        //fill the data

        //return restTemplate.postForObject("http://doctor/registerVisit/", registerVisitDTO, VisitRegisterResponseDTO.class);
        return new VisitRegisterResponseDTO();
    }
}
