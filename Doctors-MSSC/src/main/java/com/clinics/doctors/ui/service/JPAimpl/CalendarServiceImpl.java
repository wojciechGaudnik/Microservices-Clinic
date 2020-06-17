package com.clinics.doctors.ui.service.JPAimpl;

import com.clinics.common.DTO.request.outer.doctor.CalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.data.model.Appointment;
import com.clinics.doctors.data.model.Calendar;
import com.clinics.doctors.data.repositorie.CalendarRepository;
import com.clinics.doctors.ui.service.CalendarService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class CalendarServiceImpl implements CalendarService {

	final private CalendarRepository calendarRepository;
	final private DoctorServiceImpl doctorServiceImpl;
	final private DoctorMedicalUnitClientImpl doctorMedicalUnitClientImpl;
	final private ModelMapper modelMapper;

	public CalendarServiceImpl(
			CalendarRepository calendarRepository,
			DoctorServiceImpl doctorServiceImpl,
			DoctorMedicalUnitClientImpl doctorMedicalUnitClientImpl,
			ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.doctorServiceImpl = doctorServiceImpl;
		this.doctorMedicalUnitClientImpl = doctorMedicalUnitClientImpl;
		this.modelMapper = modelMapper;
		modelMapper.createTypeMap(Calendar.class, CalendarResponseDTO.class).setPostConverter(getCalendarConverter());
	}

	public List<CalendarResponseDTO> getAllDoctorCalendarsDTO(UUID doctorUUID) {
		return calendarRepository
				.getCalendarsByDoctor_DoctorUUID(doctorUUID).stream()
				.map(doctor -> modelMapper.map(doctor, CalendarResponseDTO.class))
				.collect(Collectors.toList());
	}

	public CalendarResponseDTO getDoctorCalendarDTO(UUID doctorUUID, UUID calendarUUID) {
		var calendar = getDoctorCalendar(doctorUUID, calendarUUID);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public Calendar getCalendar(UUID calendarUUID) {
		var optionalCalendar = calendarRepository.getCalendarByCalendarUUID(calendarUUID);
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("No such calendar in system %s", calendarUUID ));
		}
		return optionalCalendar.get();
	}

	public Calendar getDoctorCalendar(UUID doctorUUID, UUID calendarUUID) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		var optionalCalendar = doctor.getCalendars().stream().filter(c -> c.getCalendarUUID().equals(calendarUUID)).findFirst();
		if (optionalCalendar.isEmpty()) {
			throw new NoSuchElementException(String.format("Doctor doesn't have such calendar %s", calendarUUID ));
		}
		return optionalCalendar.get();
	}

	public CalendarResponseDTO saveMedicalUniteIntoDoctorCalendar(UUID doctorUUID, CalendarDTO calendarDTO) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		var calendar = modelMapper.map(calendarDTO, Calendar.class);
		calendar.setCalendarUUID(UUID.randomUUID());
		calendar.setDoctor(doctor);
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public CalendarResponseDTO saveMedicalUniteIntoDoctorCalendar(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID) {
		doctorServiceImpl.getByUUID(doctorUUID);
		doctorMedicalUnitClientImpl.getMedicalUnitResponseDTO(medicalUniteUUID);
		var calendar = getCalendar(calendarUUID);
		if (calendar.getMedicalUnitUUID() != null) {
			throw new NoSuchElementException("Calendar already assigned to medicalUnite");
		}
		calendar.setMedicalUnitUUID(medicalUniteUUID);
		calendarRepository.save(calendar);
		return modelMapper.map(calendar, CalendarResponseDTO.class);
	}

	public void editCalendar(UUID doctorUUID, UUID calendarUUID, CalendarDTO calendarDTO) {
		var calendar = getDoctorCalendar(doctorUUID, calendarUUID);
		modelMapper.map(calendarDTO, calendar);
		calendarRepository.save(calendar);
	}

	public void editCalendarMedicalUnite(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID) {
		var calendar = getDoctorCalendar(doctorUUID, calendarUUID);
		if (calendar.getMedicalUnitUUID().equals(medicalUniteUUID)) {
			throw new NoSuchElementException("Calendar is assigned to this medicalUnite");
		}
		calendar.setMedicalUnitUUID(medicalUniteUUID);
		doctorMedicalUnitClientImpl.getMedicalUnitResponseDTO(medicalUniteUUID);
		calendarRepository.save(calendar);
	}

	public void deleteDoctorCalendar(UUID doctorUUID, UUID calendarUUID) {
		var calendar = getDoctorCalendar(doctorUUID, calendarUUID);
		calendarRepository.delete(calendar);
	}

	public void removeMedicalUniteFromCalendar(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID) {
		var calendar = getDoctorCalendar(doctorUUID, calendarUUID);
		if (calendar.getMedicalUnitUUID() == null || !calendar.getMedicalUnitUUID().equals(medicalUniteUUID)) {
			throw new NoSuchElementException("Calendar isn't assigned to this medicalUnite");
		}
		calendar.setMedicalUnitUUID(null);
		calendarRepository.save(calendar);
	}

	private Converter<Calendar, CalendarResponseDTO> getCalendarConverter() {
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