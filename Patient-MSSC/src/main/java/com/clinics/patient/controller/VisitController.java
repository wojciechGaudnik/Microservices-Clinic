package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.VisitDTO;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.service.VisitService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients/{uuid}/visit")
public class VisitController {
    final private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public ResponseEntity<Visit> registerVisit(@PathVariable UUID uuid, @RequestBody VisitDTO visitDTO, HttpServletRequest request){
        return ResponseEntity.status(201).body(visitService.registerVisit(uuid, visitDTO));
    }

    @GetMapping(value = "/{visitUUID}")
    public Visit getVisit(@PathVariable UUID visitUUID){
        return visitService.findByUuid(visitUUID);
    }

    @DeleteMapping(value = "/{visitUUID}")
    public void cancelVisit(@PathVariable UUID visitUUID){
        System.out.println(visitUUID);
        visitService.deleteByUuid(visitUUID);
    }

    //change description or status ONLY
    @PatchMapping(value = "/{visitUUID}")
    public void editVisit(@PathVariable UUID visitUUID, @Valid @RequestBody VisitDTO visitDTO){
        visitService.editVisit(visitUUID, visitDTO);
    }
}



