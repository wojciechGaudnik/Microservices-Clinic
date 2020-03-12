package com.clinics.patient.entity;

import com.clinics.common.patient.VisitStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@Table(name = "visit")
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false)
    private UUID visitUUID = UUID.randomUUID();

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @Column(updatable = false, nullable = false)
    private UUID doctorUUID;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(
            targetEntity=Patient.class,
            cascade={CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Size(max = 1000, message = "Description length out of range")
    private String description = null;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(targetEntity = Disease.class)
    @JoinTable(
            name = "disease_visit",
            joinColumns = {@JoinColumn(name = "visit_id")},
            inverseJoinColumns = {@JoinColumn(name = "disease_id")})
    private Collection<Disease> diseases = new ArrayList<>();;
}
