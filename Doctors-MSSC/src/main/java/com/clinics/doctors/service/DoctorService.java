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

import java.util.UUID;

@Slf4j
@Service
public class DoctorService {

	private Doctor doctor;
	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
	}

	public DoctorResponseDTO getDoctorByUUID(UUID UUID) {
		return new DoctorResponseDTO();
//		return (doctorRepository.findByUuid(UUID).getLicence() == null)? "There is no such doctor" : doctorRepository.findByUuid(UUID).getLicence();
	}
	public DoctorResponseDTO saveDoctor(RegisterDoctorDTO registerDoctorDTO) {
		log.warn(registerDoctorDTO.getFirstName());
		log.warn(registerDoctorDTO.getLastName());
		log.warn(registerDoctorDTO.getPhotoUrl());
		log.warn(registerDoctorDTO.getLicence());
		log.warn(String.valueOf(registerDoctorDTO.getDoctor_uuid()));
		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
		log.warn((doctor.getDoctor_uuid()) + " <-------------- doctor.getDoctor_uuid()");

		doctorRepository.save(doctor);
		DoctorResponseDTO doctorResponseDTO = modelMapper.map(doctor, DoctorResponseDTO.class);


//		String token = header.replace(TOKEN_PREFIX, "");
//		try {
//			Claims claims = Jwts.parser()
//					.setSigningKey(TOKEN_SECRET)
//					.parseClaimsJws(token)
//					.getBody();
//			String userName = claims.getSubject();
//			if (userName != null) {
//				List<String> authorities = (List<String>) claims.get("authorities");
//				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//						userName,
//						claims.get("UUID"),
//						authorities
//								.stream()
//								.map(SimpleGrantedAuthority::new)
//								.collect(Collectors.toList()));
//				SecurityContextHolder.getContext().setAuthentication(auth);
//			}
//		} catch (Exception e){
//			SecurityContextHolder.clearContext();
//		}



//		userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
//		userRepository.save(userAuth);
//		var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
//		String token = makeJwtToken(userAuth);
//		userResponse.setToken(TOKEN_PREFIX + token);
//		return userResponse;
		return doctorResponseDTO;
	}
//	public String getDoctorByID(Long id) {  //todo remove because ID !!!
//		if (doctorRepository.findById(id).isEmpty()) {
//			throw new DoctorServiceException("Id not found");
//		}
//		return doctorRepository.findById(id).get().getLicence();

//	}
}
