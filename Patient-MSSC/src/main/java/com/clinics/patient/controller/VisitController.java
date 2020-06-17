package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.EditVisitDTO;
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
@JsonDeserialize(using = LocalDateDeserializer.class)
@RequestMapping(value = "/patients/{patientUUID}/visit")
public class VisitController {
    final private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<Visit> registerVisit(@PathVariable UUID patientUUID, @RequestBody VisitDTO visitDTO, HttpServletRequest request){
        return ResponseEntity.status(201).body(visitService.registerVisit(patientUUID, visitDTO));
    }

    @GetMapping(value = "/{visitUUID}")
    public Visit getVisit(@PathVariable UUID visitUUID){
        return visitService.findByUuid(visitUUID);
    }

    @DeleteMapping(value = "/{visitUUID}")
    public void cancelVisit(@PathVariable UUID visitUUID){
        visitService.deleteByUuid(visitUUID);
    }

    //change description or status ONLY
    @PatchMapping(value = "/{visitUUID}")
    public void editVisit(@PathVariable UUID visitUUID, @Valid @RequestBody EditVisitDTO editVisitDTO){
        visitService.editVisit(visitUUID, editVisitDTO);
    }
}



