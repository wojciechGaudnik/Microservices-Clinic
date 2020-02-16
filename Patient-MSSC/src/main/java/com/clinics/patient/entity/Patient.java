package com.clinics.patient.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private UUID uuid;
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

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit> visits;
}
