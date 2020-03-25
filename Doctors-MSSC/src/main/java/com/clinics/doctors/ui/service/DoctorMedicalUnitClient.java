package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorMedicalUnitClient {
	List<MedicalUnitResponseDTO> getAll(UUID doctorUUID);
	MedicalUnitResponseDTO get(UUID doctorUUID, UUID medicalUnitUUID);
	MedicalUnitResponseDTO save(UUID medicalUnitUUID, UUID doctorUUID);
	void delete(UUID doctorUUID, UUID medicalUnitUUID);
}
