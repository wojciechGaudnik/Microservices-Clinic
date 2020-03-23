package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.response.outer.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorPatientClient {

	List<PatientResponseDTO> getAll(UUID doctorUUID);
	PatientResponseDTO get(UUID doctorUUID, UUID patientUUID);
	PatientResponseDTO save(UUID patientUUID, UUID doctorUUID);
	void delete(UUID doctorUUID, UUID patientUUID);
}
