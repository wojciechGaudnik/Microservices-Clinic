package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AddEditCalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.model.Doctor;
import com.clinics.doctors.ui.repositorie.CalendarRepository;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class CalendarService {

	final private CalendarRepository calendarRepository;
	final private DoctorRepository doctorRepository;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment;

	public CalendarService(
			CalendarRepository calendarRepository,
			DoctorRepository doctorRepository,
			ModelMapper modelMapper,
			RestTemplate restTemplate,
			Environment environment) {
		this.calendarRepository = calendarRepository;
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
	}

	public List<com.clinics.common.DTO.response.outer.CalendarResponseDTO> getDoctorCalendars(UUID doctorUUID) {
		return calendarRepository
				.getCalendarsByDoctor_DoctorUUID(doctorUUID).stream()
				.map(doctor -> modelMapper.map(doctor, com.clinics.common.DTO.response.outer.CalendarResponseDTO.class))
				.collect(Collectors.toList());
	}

	public CalendarResponseDTO save(AddEditCalendarDTO addEditCalendarDTO, UUID doctorUUID) {
		Optional<Doctor> optionalDoctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if (optionalDoctor.isEmpty()) {
			throw new NoSuchElementException(String.format("No such doctor in system %s", doctorUUID));
		}
		Calendar calendar = modelMapper.map(addEditCalendarDTO, Calendar.class);
		calendar.setCalendarUUID(UUID.randomUUID());
		calendar.setDoctor(optionalDoctor.get());
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}


	public void edit(AddEditCalendarDTO addEditCalendarDTO, UUID calendarUUID) {
		Optional<Calendar> optionalCalendar = calendarRepository.getCalendarByCalendarUUID(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		var calendar = optionalCalendar.get();
		modelMapper.map(addEditCalendarDTO, calendar);
		calendarRepository.save(calendar);
	}

	public void delete(UUID calendarUUID) {
		calendarRepository.deleteByCalendarUUID(calendarUUID);
	}

	private CalendarResponseDTO get(UUID calendarUUID) {
		Optional<Calendar> optionalCalendar = calendarRepository.getCalendarByCalendarUUID(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		return modelMapper.map(optionalCalendar.get(), CalendarResponseDTO.class);
	}
}
