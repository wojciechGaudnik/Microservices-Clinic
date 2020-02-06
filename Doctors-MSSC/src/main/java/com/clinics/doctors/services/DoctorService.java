package com.clinics.doctors.services;

import org.springframework.stereotype.Service;

@Service
public class DoctorService {
	public String getDoctorByUUID(String UUID) {
		return "Doctor test " + UUID;
	}
}
