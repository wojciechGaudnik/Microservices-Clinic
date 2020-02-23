package com.clinics.doctors.service;

import com.clinics.common.DTO.request.RegisterDoctorDTO;
import com.clinics.common.DTO.response.DoctorResponseDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.doctors.model.Doctor;
import com.clinics.doctors.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DoctorService {

	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, RestTemplate restTemplate, Environment environment) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
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

		String url = String.format("http://auth/%s", doctor.getDoctoruuid());
		var responseFromAuth = restTemplate.getForEntity(url, UserResponseDTO.class);
		log.warn(responseFromAuth.getStatusCode() + " <---------------- Status Code ");


		doctorRepository.save(doctor);
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}
}
