package com.clinics.patient.controller;

import com.clinics.common.DTO.request.VisitDTO;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/patient/visit")
public class VisitController {
    final private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public VisitDTO registerVisit(@RequestBody VisitDTO visitDTO){
        return visitService.registerVisit(visitDTO);
    }
    //TODO otworz swoja historie wizyt
    //TODO getVisitsByPatientById()
    //TODO odwolaj wizyte

    //dane pacjenta dla wizyty
    @GetMapping(path = "/{UUID}")
    public Visit getAllDetails(@PathVariable UUID UUID){
        return visitService.findAllDetails(UUID);
    }
}



