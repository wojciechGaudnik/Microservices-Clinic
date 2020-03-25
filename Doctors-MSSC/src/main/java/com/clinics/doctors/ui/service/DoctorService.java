package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.EditDoctorDTO;
import com.clinics.common.DTO.request.outer.doctor.RegisterDoctorDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.doctors.data.model.Doctor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public interface DoctorService {

	List<DoctorResponseDTO> getAllDTO();
	DoctorResponseDTO getDTOByUUID(UUID doctorUUID);
	Doctor getByUUID(UUID doctorUUID);
	DoctorResponseDTO save(RegisterDoctorDTO registerDoctorDTO, HttpServletRequest request);
	void edit(EditDoctorDTO editDoctorDTO, UUID uuid, HttpServletRequest request);
	void delete(UUID uuid);
}