package com.clinics.patient.controller;

import com.clinics.common.DTO.request.RegisterVisitDTO;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class VisitController {

    @Autowired
    VisitService visitService;

    @PostMapping("/visit/{uuid}")
    public Visit registerVisit(@RequestBody Visit visit, @RequestParam UUID uuid){
        return visitService.registerVisit(visit, uuid);
    }

    //otworz swoja historie wizyt
    //getVisitsByPatientById()
    //odwolaj wizyte
}
