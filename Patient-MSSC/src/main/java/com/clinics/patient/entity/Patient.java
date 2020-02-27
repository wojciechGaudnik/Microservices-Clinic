package com.clinics.patient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@DynamicInsert
@DynamicUpdate
@Table(name = "patient")
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();

    private String pesel;

    @NotBlank(message = "fistName is mandatory")
    @Size(min = 2, max = 100, message = "firstName length out of range")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    @Size(min = 3, max = 100, message = "lastName length out of range")
    private String lastName;

    @Column(unique = true)
    @Size(min = 3, max = 100, message = "length out of range ")
    private String photoUrl;

    @ToString.Exclude
    @OneToMany(
            targetEntity=Visit.class,
            mappedBy="patient",
            cascade={CascadeType.ALL}, fetch = FetchType.LAZY,
            orphanRemoval=true)
    private List<Visit> visits = new ArrayList<>();
}
