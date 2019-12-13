package com.eureka.auth.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "medicalUser")
public class MedicalUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;
    private String role;
}
