package com.clinics.patient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "visit")
@JsonIgnoreProperties(value = "id")
public class Visit {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;
    private UUID doctorUUID;

    @ManyToOne()
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    private String description;

    public Visit() {}

    //depending on implementation of calendar/terms in doctor service
    //maybe not needed if we stick to dateTime
    //private Long termId;
}
