package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.AddEditCalendarDTO;
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

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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

	public List<Calendar> getDoctorCalendars(UUID uuid) {
		return calendarRepository.getAllByDoctor_Doctoruuid(uuid);
	}

	public CalendarResponseDTO save(AddEditCalendarDTO addEditCalendarDTO, UUID doctorUUID) {
		Doctor doctor = getDoctor(doctorUUID);
		Calendar calendar = modelMapper.map(addEditCalendarDTO, Calendar.class);
		calendar.setCalendaruuid(UUID.randomUUID());
		calendar.setDoctor(doctor);
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public void edit(AddEditCalendarDTO addEditCalendarDTO, UUID calendarUUID) {
		Calendar calendar = getCalendar(calendarUUID);
		modelMapper.map(addEditCalendarDTO, calendar);
		calendarRepository.save(calendar);
	}

	public void delete(UUID calendarUUID) {
		log.error("---> 3 <---");
		calendarRepository.deleteByCalendaruuid(calendarUUID);
		log.error("---> 4 <---");
	}

	private Doctor getDoctor(UUID doctorUUID) {
		Optional<Doctor> optionalDoctor = doctorRepository.findByDoctoruuid(doctorUUID);
		if (optionalDoctor.isEmpty()) {
			throw new NoSuchElementException(String.format("No such doctor in system %s", doctorUUID ));
		}
		return optionalDoctor.get();
	}

	private Calendar getCalendar(UUID calendarUUID) {
		Optional<Calendar> optionalCalendar = calendarRepository.getCalendarByCalendaruuid(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		return optionalCalendar.get();
	}
}
