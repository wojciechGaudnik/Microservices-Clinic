package com.clinics.patient.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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
    private UUID uuid = UUID.randomUUID();

    //TODO na localDateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private UUID doctorUUID;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(
            targetEntity=Patient.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    private String description;
}
