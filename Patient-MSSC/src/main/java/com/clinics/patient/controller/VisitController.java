package com.clinics.patient.controller;

import com.clinics.common.DTO.request.VisitDTO;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/patient/{uuid}/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @PostMapping
    public VisitDTO registerVisit(@RequestBody VisitDTO visitDTO, @PathVariable(name = "uuid") UUID patientUUID){
        return visitService.registerVisit(visitDTO, patientUUID);
    }

    //otworz swoja historie wizyt
    //getVisitsByPatientById()
    //odwolaj wizyte
}
