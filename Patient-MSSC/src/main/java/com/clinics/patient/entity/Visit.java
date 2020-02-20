package com.clinics.patient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    private LocalDateTime date;
    private UUID doctorUUID;

    @ManyToOne()
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    private String description;

    //depending on implementation of calendar/terms in doctor service
    //maybe not needed if we stick to dateTime
    //private Long termId;
}
