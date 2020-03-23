package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.CalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.data.model.Calendar;

import java.util.List;
import java.util.UUID;

public interface CalendarService {

	List<CalendarResponseDTO> getAllDoctorCalendarsDTO(UUID doctorUUID);
	CalendarResponseDTO getDoctorCalendarDTO(UUID doctorUUID, UUID calendarUUID);
	Calendar getCalendar(UUID calendarUUID);
	Calendar getDoctorCalendar(UUID doctorUUID, UUID calendarUUID);
	CalendarResponseDTO saveMedicalUniteIntoDoctorCalendar(UUID doctorUUID, CalendarDTO calendarDTO);
	CalendarResponseDTO saveMedicalUniteIntoDoctorCalendar(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID);
	void editCalendar(UUID doctorUUID, UUID calendarUUID, CalendarDTO calendarDTO);
	void editCalendarMedicalUnite(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID);
	void deleteDoctorCalendar(UUID doctorUUID, UUID calendarUUID);
	void removeMedicalUniteFromCalendar(UUID doctorUUID, UUID calendarUUID, UUID medicalUniteUUID);
}
