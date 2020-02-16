package com.clinics.patient.controller;

import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patient/{uuid}/visit")
public class VisitController {

    @Autowired
    VisitService visitService;

    @PostMapping
    public Visit registerVisit(@RequestBody Visit visit, @PathVariable(name = "uuid") UUID patientUUID){
        return visitService.registerVisit(visit, patientUUID);
    }

    //otworz swoja historie wizyt
    //getVisitsByPatientById()
    //odwolaj wizyte
}
