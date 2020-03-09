package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/patient/{uuid}/visit")
public class VisitController {
    final private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public VisitDTO registerVisit(@PathVariable UUID uuid, @RequestBody VisitDTO visitDTO){
        return visitService.registerVisit(uuid, visitDTO);
    }

    @GetMapping(path = "/{uuid}")
    public Visit getVisit(@PathVariable UUID UUID){
        return visitService.findAllDetails(UUID);
    }
}



