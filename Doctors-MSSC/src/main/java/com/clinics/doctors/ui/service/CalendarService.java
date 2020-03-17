package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AddEditCalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.ui.model.Appointment;
import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.repositorie.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class CalendarService {

	final private CalendarRepository calendarRepository;
	final private DoctorService doctorService;
	final private DoctorMedicalUnitService doctorMedicalUnitService;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment;

	public CalendarService(
			CalendarRepository calendarRepository,
			DoctorService doctorService,
			DoctorMedicalUnitService doctorMedicalUnitService,
			RestTemplate restTemplate,
			Environment environment,
			ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.doctorService = doctorService;
		this.doctorMedicalUnitService = doctorMedicalUnitService;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.modelMapper = modelMapper;
		modelMapper.createTypeMap(Calendar.class, CalendarResponseDTO.class).setPostConverter(getConverterCalendarIntoDTO());
	}

	public List<CalendarResponseDTO> getDoctorCalendars(UUID doctorUUID) {
		return calendarRepository
				.getCalendarsByDoctor_DoctorUUID(doctorUUID).stream()
				.map(doctor -> modelMapper.map(doctor, CalendarResponseDTO.class))
				.collect(Collectors.toList());
	}

	public CalendarResponseDTO getDoctorCalendar(UUID doctorUUID, UUID calendarUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		if (doctor.getCalendars().stream().noneMatch(calendar -> calendar.getCalendarUUID().equals(calendarUUID))) {
			throw new NoSuchElementException(String.format("Doctor doesn't have such calendar %s", calendarUUID ));
		}
		var calendar = getCalendar(calendarUUID);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public CalendarResponseDTO save(AddEditCalendarDTO addEditCalendarDTO, UUID doctorUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		Calendar calendar = modelMapper.map(addEditCalendarDTO, Calendar.class);
		calendar.setCalendarUUID(UUID.randomUUID());
		calendar.setDoctor(doctor);
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public CalendarResponseDTO save(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID) {
		doctorService.getDoctor(doctorUUID);
		doctorMedicalUnitService.getMedicalUnitResponseDTO(medicalUniteUUID);
		var calendar = getCalendar(calendarUUID);
		if (calendar.getMedicalUnitUUID() != null) {
			throw new NoSuchElementException("Calendar already assigned to medicalUnite");
		}
		calendar.setMedicalUnitUUID(medicalUniteUUID);
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}


	public void edit(AddEditCalendarDTO addEditCalendarDTO, UUID calendarUUID) {
		var optionalCalendar = calendarRepository.getCalendarByCalendarUUID(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		var calendar = optionalCalendar.get();
		modelMapper.map(addEditCalendarDTO, calendar);
		calendarRepository.save(calendar);
	}

	public void delete(UUID calendarUUID) {
		var calendar = getCalendar(calendarUUID);
		calendarRepository.delete(calendar);
	}

	public void delete(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		var optionalCalendar = doctor.getCalendars().stream().filter(c -> c.getCalendarUUID().equals(calendarUUID)).findFirst();
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("Doctor doesn't have such calendar %s", calendarUUID ));
		}
		var calendar = optionalCalendar.get();
		if (optionalCalendar.get().getMedicalUnitUUID() == null) {
			throw new NoSuchElementException("Calendar isn't assigned to medicalUnite");
		}
		calendar.setMedicalUnitUUID(null);
		calendarRepository.save(calendar);
	}

	private CalendarResponseDTO get(UUID calendarUUID) {
		return modelMapper.map(getCalendar(calendarUUID), CalendarResponseDTO.class);
	}

	public Calendar getCalendar(UUID calendarUUID) {
		Optional<Calendar> optionalCalendar = calendarRepository.getCalendarByCalendarUUID(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		return optionalCalendar.get();
	}

	private Converter<Calendar, CalendarResponseDTO> getConverterCalendarIntoDTO() {
		Converter<Calendar, CalendarResponseDTO> converter = new Converter<>() {
			@Override
			public CalendarResponseDTO convert(MappingContext<Calendar, CalendarResponseDTO> context) {
				Collection<Appointment> appointments = context.getSource().getAppointments();
				if(appointments == null) return context.getDestination();
				context
						.getDestination()
						.setAppointmentsUUID(appointments
								.stream()
								.map(Appointment::getAppointmentUUID)
								.collect(Collectors.toList()));
				return context.getDestination();
			}
		};
		return converter;
	}
}
