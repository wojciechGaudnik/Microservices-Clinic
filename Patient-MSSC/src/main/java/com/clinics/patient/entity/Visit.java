package com.clinics.patient.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private Date date;
    private UUID doctorUUID;

    @ManyToOne
    private Patient patient;

    private String description;
}
