package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.AppointmentDTO;
import com.clinics.common.DTO.response.outer.AppointmentResponseDTO;
import com.clinics.doctors.ui.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/calendars/{calendarUUID}/appointments")
public class DoctorAppointmentController {

	final private AppointmentService appointmentService;

	public DoctorAppointmentController(
			AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping
	public ResponseEntity<List<AppointmentResponseDTO>> getAllCalendarAppointments(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID){
		return ResponseEntity.ok().body(appointmentService.getAllDoctorAppointments(doctorUUID, calendarUUID));
	}

	@GetMapping(value = "/{appointmentUUID}")
	public ResponseEntity<AppointmentResponseDTO> getCalendarAppointment(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID appointmentUUID){
		return ResponseEntity.ok().body(appointmentService.getDoctorAppointment(doctorUUID, calendarUUID, appointmentUUID));
	}

	@PostMapping
	public ResponseEntity<AppointmentResponseDTO> addAppointmentIntoCalendar(
			@Valid @RequestBody AppointmentDTO appointmentDTO,
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID){
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.saveAppointment(doctorUUID, calendarUUID, appointmentDTO));
	}

	@PostMapping(value = "/multiple")
	public ResponseEntity<List<AppointmentResponseDTO>> addListAppointmentsIntoCalendar(
			@Valid @RequestBody List<AppointmentDTO> addEditAppointmentsDTO,
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID){
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.saveAppointments(doctorUUID, calendarUUID, addEditAppointmentsDTO));
	}

	@PatchMapping(value = "/{appointmentUUID}")
	public ResponseEntity<Void> editCalendarAppointment(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID appointmentUUID,
			@Valid @RequestBody AppointmentDTO appointmentDTO){
		appointmentService.editAppointment(doctorUUID, calendarUUID, appointmentUUID, appointmentDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{appointmentUUID}")
	public ResponseEntity<Void> deleteCalendarAppointment(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID appointmentUUID){
		appointmentService.deleteAppointment(doctorUUID, calendarUUID, appointmentUUID);
		return ResponseEntity.ok().build();
	}
}
