package com.clinics.patient.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private UUID uuid;
    private String pesel;

    @OneToMany
    private List<Visit> visits = new ArrayList<>();
}
