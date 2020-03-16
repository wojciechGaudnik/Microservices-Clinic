package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AddEditAppointmentDTO;
import com.clinics.common.DTO.response.outer.AppointmentResponseDTO;
import com.clinics.doctors.ui.model.Appointment;
import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.repositorie.RepositoryAppointment;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class AppointmentService {

	final private RepositoryAppointment appointmentRepository;
	final private CalendarService calendarService;
	final private DoctorService doctorService;
	final private ModelMapper modelMapper;

	public AppointmentService(
			CalendarService calendarService,
			DoctorService doctorService,
			RepositoryAppointment appointmentRepository,
			ModelMapper modelMapper) {
		this.calendarService = calendarService;
		this.doctorService = doctorService;
		this.appointmentRepository = appointmentRepository;
		this.modelMapper = modelMapper;
	}

	public List<AppointmentResponseDTO> getAllAppointments(UUID doctorUUID, UUID calendarUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		if (doctor.getCalendars().stream().noneMatch(calendar -> calendar.getCalendarUUID().equals(calendarUUID))){
			throw new NoSuchElementException(String.format("Doctor doesn't have such calendar %s", calendarUUID ));
		}
		var calendar = calendarService.getCalendar(calendarUUID);
		return calendar
				.getAppointments()
				.stream()
				.map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
				.collect(Collectors.toList());

	}

	public AppointmentResponseDTO getAllAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID) {
		var optionalAppointmentResponseDTO = getAllAppointments(doctorUUID, calendarUUID)
				.stream()
				.filter(appointmentResponseDTO -> appointmentResponseDTO.getAppointmentUUID().equals(appointmentUUID))
				.findFirst();
		if (optionalAppointmentResponseDTO.isEmpty()) {
			throw new NoSuchElementException(String.format("Calendar doesn't have such appointment %s", appointmentUUID ));
		}
		return optionalAppointmentResponseDTO.get();
	}

	public AppointmentResponseDTO save(UUID doctorUUID, UUID calendarUUID, AddEditAppointmentDTO addEditAppointmentDTO) {
		var doctor = doctorService.getDoctor(doctorUUID);
		if (doctor.getCalendars().stream().noneMatch(calendar -> calendar.getCalendarUUID().equals(calendarUUID))) {
			throw new NoSuchElementException("Doctor doesn't have such calendar");
		}
		Calendar calendar = calendarService.getCalendar(calendarUUID);
		//todo some meat left
		if (calendar
				.getAppointments()
				.stream()
				.anyMatch(appointment -> appointment.getLocalDateTime()
						.equals(addEditAppointmentDTO.getLocalDateTime()))) {
			throw new NoSuchElementException("Calendar already have appointment on this time");
		}
		var appointment = modelMapper.map(addEditAppointmentDTO, Appointment.class);
		appointment.setAppointmentUUID(UUID.randomUUID());
		appointment.setCalendar(calendar);
		appointment = appointmentRepository.save(appointment);
		return modelMapper.map(appointment, AppointmentResponseDTO.class);
	}

	public List<AppointmentResponseDTO> save(UUID doctorUUID, UUID calendarUUID, List<AddEditAppointmentDTO> listDddEditAppointmentDTO) {
		return listDddEditAppointmentDTO
				.stream()
				.map(addEditAppointmentDTO -> save(doctorUUID, calendarUUID, addEditAppointmentDTO))
				.collect(Collectors.toList());
	}
}
