package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.SpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SpecializationService {

	List<SpecializationResponseDTO> getAllSpecializations();
	List<SpecializationResponseDTO> getDoctorSpecializations(UUID doctorUUID);
	SpecializationResponseDTO getDoctorSpecialization(UUID doctorUUID, UUID specializationUUID);
	SpecializationResponseDTO getSpecializationByUUID(UUID specializationUUID);
	SpecializationResponseDTO saveSpecialization(SpecializationDTO specializationDTO);
	SpecializationResponseDTO saveSpecializationIntoDoctor(UUID doctorUUID, SpecializationDTO specializationDTO);
	SpecializationResponseDTO saveExistingSpecializationIntoDoctor(UUID doctorUUID, UUID specializationUUID);
	void editSpecialization(SpecializationDTO specializationDTO, UUID specializationUUID);
	void removeSpecializationFromDoctor(UUID doctorUUID, UUID specializationUUID);
	void deleteSpecialization(UUID specializationUUID);
}
