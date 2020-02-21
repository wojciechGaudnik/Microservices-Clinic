package com.clinics.doctors.service;

import com.clinics.common.DTO.request.RegisterDoctorDTO;
import com.clinics.common.DTO.response.DoctorResponseDTO;
import com.clinics.doctors.model.Doctor;
import com.clinics.doctors.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
	}

	//todo Optional !!! if not throw Exception and send response message from Advisor !!!  //		doctor.ifPresentOrElse
	public DoctorResponseDTO getDoctorByUUID(UUID UUID) {
		Optional<Doctor> doctor = doctorRepository.findByDoctoruuid(UUID);
		if (doctor.isPresent()) {
			return modelMapper.map(doctor.get(), DoctorResponseDTO.class);
		}
		return new DoctorResponseDTO();
	}

	public DoctorResponseDTO saveDoctor(RegisterDoctorDTO registerDoctorDTO) {
		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
		doctorRepository.save(doctor);
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}
}
