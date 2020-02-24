//package com.clinics.doctors.model;
//
//import lombok.*;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Builder(toBuilder = true)
//@DynamicInsert
//@DynamicUpdate
//@Entity
//public class Album {
//	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO)
//	Integer albumId;
//
//	String albumName;
//
//	@OneToMany(targetEntity=Photo.class,mappedBy="album",cascade={CascadeType.ALL},orphanRemoval=true)
//	Set<Photo> photos = new HashSet<>();
//}
