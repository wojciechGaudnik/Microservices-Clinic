package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.EditDoctorDTO;
import com.clinics.common.DTO.request.inner.EditUserDTO;
import com.clinics.common.DTO.request.outer.doctor.RegisterDoctorDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.doctors.data.model.Calendar;
import com.clinics.doctors.data.model.Doctor;
import com.clinics.doctors.data.repositorie.DoctorRepository;
import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional  //todo https://dzone.com/articles/how-does-spring-transactional
@Service
public class DoctorService {

	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment; //todo we will use this after set up config mssc

	public DoctorService(
			DoctorRepository doctorRepository,
			ModelMapper modelMapper,
			RestTemplate restTemplate,
			Environment environment) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.modelMapper.createTypeMap(Doctor.class, DoctorResponseDTO.class).setPostConverter(getConverterDoctorIntoDTO());
	}

	public List<DoctorResponseDTO> getAllDTO(){
		return doctorRepository
				.findAll()
				.stream()
				.map(doctor -> modelMapper.map(doctor, DoctorResponseDTO.class))
				.collect(Collectors.toList());
	}

	public DoctorResponseDTO getDTOByUUID(UUID doctorUUID) {
		return modelMapper
				.map(doctorRepository
						.findByDoctorUUID(doctorUUID)
						.orElseThrow(NoSuchElementException::new), DoctorResponseDTO.class);
	}

	public Doctor getByUUID(UUID doctorUUID) {
		var optionalDoctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if (optionalDoctor.isEmpty()) {
			throw new NoSuchElementException(String.format("No such doctor in system %s", doctorUUID));
		}
		return optionalDoctor.get();
	}

	public DoctorResponseDTO save(RegisterDoctorDTO registerDoctorDTO, HttpServletRequest request) {
		//todo we have decide what way we go, here or set up rabbit or kafka
		//todo https://dzone.com/articles/synchronous-kafka-using-spring-request-reply-1
		//todo https://springbootdev.com/2017/11/12/spring-amqp-rabbitmq-topic-exchange-example-part-1-producer-application/
//		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
//		doctorRepository.save(doctor);

		String uri = String.format("http://auth/auth/users/%s", registerDoctorDTO.getDoctorUUID());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
		HttpEntity<String> requestFromDoctor = new HttpEntity<>("Empty Request", httpHeaders);  //todo make this better, Void ?
		try {
			ResponseEntity<Void> responseFromAuth = restTemplate.exchange(uri, HttpMethod.PUT, requestFromDoctor, Void.class);  //todo remove conmpletely response from Auth ?
		} catch (Exception e) {
			throw new NoSuchElementException("There is no (dev free) such doctor in AUTH");
		}
		var doctor = modelMapper.map(registerDoctorDTO, Doctor.class);
		doctorRepository.save(doctor);
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}

	public void edit(EditDoctorDTO editDoctorDTO, UUID uuid, HttpServletRequest request) {
		String uri = String.format("http://auth/auth/users/%s", uuid);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
		if (editDoctorDTO.getPassword() != null || editDoctorDTO.getEmail() != null) {
			EditUserDTO editUserDTO = modelMapper.map(editDoctorDTO, EditUserDTO.class);
			HttpEntity<EditUserDTO> httpEntity = new HttpEntity<>(editUserDTO, httpHeaders);
			restTemplate.exchange(uri, HttpMethod.PATCH, httpEntity, Void.class);
		}
		if (doctorRepository.existsByDoctorUUID(uuid)) {
			var doctorToEdit = doctorRepository.findByDoctorUUID(uuid).get();
			modelMapper.map(editDoctorDTO, doctorToEdit);
			doctorRepository.save(doctorToEdit);
		}
	}

	public void delete(UUID uuid) {
		String uri = String.format("http://auth/auth/users/%s", uuid);
		restTemplate.delete(uri);
		doctorRepository.deleteByDoctorUUID(uuid);
	}

	private Converter<Doctor, DoctorResponseDTO> getConverterDoctorIntoDTO() {
		Converter<Doctor, DoctorResponseDTO> converter = new Converter<>() {
			@Override
			public DoctorResponseDTO convert(MappingContext<Doctor, DoctorResponseDTO> context) {
				Collection<Calendar> calendars = context.getSource().getCalendars();
				if(calendars == null) return context.getDestination();
				context
						.getDestination()
						.setCalendarsUUID(calendars
								.stream()
								.map(Calendar::getCalendarUUID)
								.collect(Collectors.toList()));
				return context.getDestination();
			}
		};
		return converter;
	}
}


