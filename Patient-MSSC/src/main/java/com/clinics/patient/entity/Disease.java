package com.clinics.patient.entity;

import com.clinics.common.patient.DiseaseName;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@Table(name = "disease")
@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false)
    private UUID diseaseUUID = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    private DiseaseName diseaseName;

    @JsonIdentityReference
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(targetEntity = Visit.class)
    @JoinTable(
            name = "disease_visit",
            joinColumns = {@JoinColumn(name = "disease_id")},
            inverseJoinColumns = {@JoinColumn(name = "visit_id")})
    private Collection<Visit> visits = new ArrayList<>();

    //TODO description so that this entity makes sense
}
