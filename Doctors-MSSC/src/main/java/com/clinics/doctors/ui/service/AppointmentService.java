package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.common.DTO.response.outer.AppointmentResponseDTO;
import com.clinics.doctors.data.model.Appointment;
import com.clinics.doctors.data.repositorie.RepositoryAppointment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class AppointmentService {

	final private RepositoryAppointment appointmentRepository;
	final private CalendarService calendarService;
	final private ModelMapper modelMapper;

	public AppointmentService(
			RepositoryAppointment appointmentRepository,
			CalendarService calendarService,
			ModelMapper modelMapper) {
		this.appointmentRepository = appointmentRepository;
		this.calendarService = calendarService;
		this.modelMapper = modelMapper;
	}

	public List<AppointmentResponseDTO> getAllDoctorAppointments(UUID doctorUUID, UUID calendarUUID) {
		var calendar = calendarService.getDoctorCalendar(doctorUUID, calendarUUID);
		return calendar
				.getAppointments()
				.stream()
				.map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
				.collect(Collectors.toList());

	}

	public AppointmentResponseDTO getDoctorAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID) {
		var optionalAppointmentResponseDTO = getAllDoctorAppointments(doctorUUID, calendarUUID)
				.stream()
				.filter(appointmentResponseDTO -> appointmentResponseDTO.getAppointmentUUID().equals(appointmentUUID))
				.findFirst();
		if (optionalAppointmentResponseDTO.isEmpty()) {
			throw new NoSuchElementException(String.format("Calendar doesn't have such appointment %s", appointmentUUID ));
		}
		return optionalAppointmentResponseDTO.get();
	}

	public AppointmentResponseDTO saveAppointment(UUID doctorUUID, UUID calendarUUID, AppointmentDTO appointmentDTO) {
		var calendar = calendarService.getDoctorCalendar(doctorUUID, calendarUUID);
		//todo some meat left
		if (calendar
				.getAppointments()
				.stream()
				.anyMatch(appointment -> appointment.getLocalDateTime()
						.equals(appointmentDTO.getLocalDateTime()))) {
			throw new NoSuchElementException("Calendar already have appointment on this time");
		}
		var appointment = modelMapper.map(appointmentDTO, Appointment.class);
		appointment.setAppointmentUUID(UUID.randomUUID());
		appointment.setCalendar(calendar);
		appointment = appointmentRepository.save(appointment);
		return modelMapper.map(appointment, AppointmentResponseDTO.class);
	}

	public List<AppointmentResponseDTO> saveAppointments(UUID doctorUUID, UUID calendarUUID, List<AppointmentDTO> listDddEditAppointmentDTO) {
		return listDddEditAppointmentDTO
				.stream()
				.map(appointmentDTO -> saveAppointment(doctorUUID, calendarUUID, appointmentDTO))
				.collect(Collectors.toList());
	}

	public void deleteAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID) {
		var calendar = calendarService.getDoctorCalendar(doctorUUID, calendarUUID);
		var appointment = calendar.getAppointments().stream().filter(a -> a.getAppointmentUUID().equals(appointmentUUID)).findFirst();
		if (appointment.isEmpty()) {
			throw new NoSuchElementException("Calendar doesn't have such appointment");
		}
		appointmentRepository.delete(appointment.get());
	}

	public void editAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID, AppointmentDTO appointmentDTO) {
		var calendar = calendarService.getDoctorCalendar(doctorUUID, calendarUUID);
		var optionalAppointment = calendar.getAppointments().stream().filter(a -> a.getAppointmentUUID().equals(appointmentUUID)).findFirst();
		if (optionalAppointment.isEmpty()) {
			throw new NoSuchElementException("Calendar doesn't have such appointment");
		}
		var appointment = optionalAppointment.get();
		modelMapper.map(appointmentDTO, appointment);
		appointmentRepository.save(appointment);
	}
}
