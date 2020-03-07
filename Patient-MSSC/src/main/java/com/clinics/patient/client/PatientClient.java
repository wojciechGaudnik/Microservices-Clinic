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
        //TODO VisitRegisterResponseDTO
        //TODO return restTemplate.postForObject("http://doctor/registerVisit/", registerVisitDTO, VisitRegisterResponseDTO.class);
        return new VisitRegisterResponseDTO();
    }
}
