//package com.clinics.doctors.model;
//
//import lombok.*;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//
//@Data
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@Builder(toBuilder = true)
//@DynamicInsert
//@DynamicUpdate
//@Entity
//public class Photo{
//	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO)
//	Integer photo_id;
//
//	String photoName;
//
//	@ManyToOne(targetEntity=Album.class)
//	@JoinColumn(name="album_id")
//	Album album;
//
//}
