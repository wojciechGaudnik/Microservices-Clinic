package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.common.DTO.response.outer.AppointmentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

	List<AppointmentResponseDTO> getAllDoctorAppointments(UUID doctorUUID, UUID calendarUUID);
	AppointmentResponseDTO getDoctorAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID);
	AppointmentResponseDTO saveAppointment(UUID doctorUUID, UUID calendarUUID, AppointmentDTO appointmentDTO);
	List<AppointmentResponseDTO> saveAppointments(UUID doctorUUID, UUID calendarUUID, List<AppointmentDTO> listDddEditAppointmentDTO);
	void editAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID, AppointmentDTO appointmentDTO);
	void deleteAppointment(UUID doctorUUID, UUID calendarUUID, UUID appointmentUUID);
}
