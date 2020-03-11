package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.EditDoctorDTO;
import com.clinics.common.DTO.request.inner.EditUserDTO;
import com.clinics.common.DTO.request.outer.RegisterDoctorDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.doctors.ui.model.Doctor;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Slf4j
@Service
public class DoctorService {

	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment;

	public DoctorService(
			DoctorRepository doctorRepository,
			ModelMapper modelMapper,
			RestTemplate restTemplate,
			Environment environment) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
	}

	//todo Optional !!! if not throw Exception and send response message from Advisor !!!  //		doctor.ifPresentOrElse
	//	Optional<MedicalUnit> optionalMedicalUnit = medicalUnitRepository.findByMedicalunituuid(UUID);
//		return modelMapper
//				.map(optionalMedicalUnit.
//				orElseThrow(NoSuchElementException::new), MedicalUnitResponseDTO .class);
	public DoctorResponseDTO getByUUID(UUID UUID) {
		Optional<Doctor> doctor = doctorRepository.findByDoctoruuid(UUID);
		if (doctor.isPresent()) {
			return modelMapper.map(doctor.get(), DoctorResponseDTO.class);
		}
		return new DoctorResponseDTO();
	}

	public DoctorResponseDTO save(RegisterDoctorDTO registerDoctorDTO, HttpServletRequest request) {
		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
		doctorRepository.save(doctor);

		String uri = String.format("http://auth/auth/users/%s", registerDoctorDTO.getDoctoruuid());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtProperties.AUTHORIZATION_HEADER, request.getHeader(JwtProperties.AUTHORIZATION_HEADER));
		HttpEntity<String> requestFromDoctor = new HttpEntity<>("Empty Request", httpHeaders);  //todo make this better, Void ?

		//todo make the same way as edit !!!
		try {
			ResponseEntity<Void> responseFromAuth = restTemplate.exchange(uri, HttpMethod.PUT, requestFromDoctor, Void.class);  //todo remove conmpletely response from Auth ?
		} catch (Exception e) {
			throw new NoSuchElementException("There is no (dev free) such doctor in AUTH");
		}
//		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
//		doctorRepository.save(doctor);
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}

	public void edit(EditDoctorDTO editDoctorDTO, UUID uuid, HttpServletRequest request) {
		String uri = String.format("http://auth/auth/users/%s", uuid);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtProperties.AUTHORIZATION_HEADER, request.getHeader(JwtProperties.AUTHORIZATION_HEADER));
		if (editDoctorDTO.getPassword() != null || editDoctorDTO.getEmail() != null) {
			EditUserDTO editUserDTO = modelMapper.map(editDoctorDTO, EditUserDTO.class);
			HttpEntity<EditUserDTO> httpEntity = new HttpEntity<>(editUserDTO, httpHeaders);
			restTemplate.exchange(uri, HttpMethod.PATCH, httpEntity, Void.class);
		}
		if (doctorRepository.existsByDoctoruuid(uuid)) {
			var doctorToEdit = doctorRepository.findByDoctoruuid(uuid).get();
			modelMapper.map(editDoctorDTO, doctorToEdit);
			doctorRepository.save(doctorToEdit);
		}
	}

	public void delete(UUID uuid) {
		String uri = String.format("http://auth/auth/users/%s", uuid);
		restTemplate.delete(uri);
		doctorRepository.deleteByDoctoruuid(uuid);
	}

//	public List<Calendar> getDoctorCalendars(UUID uuid) {
//		return calendarRepository.getAllByDoctor_Doctoruuid(uuid);
//	}
}


