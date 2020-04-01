package com.clinics.patient.controller;

import com.clinics.common.DTO.request.outer.DiseaseDTO;
import com.clinics.patient.entity.Disease;
import com.clinics.patient.service.DiseaseService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@JsonDeserialize(using = LocalDateDeserializer.class)
@RequestMapping(value = "patients/{patientUUID}/visits/{visitUUID}/disease")
public class DiseaseController {
    final private DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping
    public ResponseEntity<Disease> addDisease(@PathVariable UUID visitUUID, HttpServletRequest request, @RequestBody DiseaseDTO diseaseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(diseaseService.addDisease(visitUUID, diseaseDTO));
    }

    @DeleteMapping(value = "/{diseaseUUID}")
    public void removeDisease(@PathVariable UUID visitUUID, @PathVariable UUID diseaseUUID, HttpServletRequest request){
        diseaseService.removeDisease(visitUUID, diseaseUUID);
    }
}
