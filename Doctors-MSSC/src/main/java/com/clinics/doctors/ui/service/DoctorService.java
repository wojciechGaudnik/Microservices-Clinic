package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.RegisterDoctorDTO;
import com.clinics.common.DTO.response.DoctorResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.doctors.ui.model.Doctor;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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

	public DoctorResponseDTO saveDoctor(RegisterDoctorDTO registerDoctorDTO, HttpServletRequest request) {
		String url = String.format("http://auth/auth/users/%s", registerDoctorDTO.getDoctoruuid());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
		HttpEntity<String> requestFromDoctor = new HttpEntity<>("Empty Request", httpHeaders);
		try {
			ResponseEntity<Void> responseFromAuth = restTemplate.exchange(url, HttpMethod.PUT, requestFromDoctor, Void.class);
		} catch (Exception e) {
			throw new NoSuchElementException("There is no such doctor in AUTH");
		}
		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
		doctorRepository.save(doctor);
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}
}
