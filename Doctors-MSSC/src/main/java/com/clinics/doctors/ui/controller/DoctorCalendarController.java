package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.CalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.ui.service.JPAimpl.CalendarServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/calendars")
public class DoctorCalendarController {

	final private CalendarServiceImpl calendarServiceImpl;

	public DoctorCalendarController(
			CalendarServiceImpl calendarServiceImpl){
		this.calendarServiceImpl = calendarServiceImpl;
	}

	@GetMapping
	public ResponseEntity<List<CalendarResponseDTO>> getALLDoctorCalendars(
			@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(calendarServiceImpl.getAllDoctorCalendarsDTO(doctorUUID));
	}

	@GetMapping(value = "/{calendarUUID}")
	public ResponseEntity<CalendarResponseDTO> getDoctorCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID){
		return ResponseEntity.ok().body(calendarServiceImpl.getDoctorCalendarDTO(doctorUUID, calendarUUID));
	}

	@PostMapping
	public ResponseEntity<CalendarResponseDTO> addCalendarIntoDoctor(
			@Valid @RequestBody CalendarDTO calendarDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calendarServiceImpl.saveMedicalUniteIntoDoctorCalendar(doctorUUID, calendarDTO));
	}

	@PatchMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> editDoctorCalendar(
			@Valid @RequestBody CalendarDTO calendarDTO,
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID) {
		calendarServiceImpl.editCalendar(doctorUUID, calendarUUID, calendarDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> deleteDoctorCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID) {
		calendarServiceImpl.deleteDoctorCalendar(doctorUUID, calendarUUID);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/{calendarUUID}/medical-unites/{medicalUniteUUID}")
	public ResponseEntity<CalendarResponseDTO> addMedicalUniteIntoCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID medicalUniteUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calendarServiceImpl.saveMedicalUniteIntoDoctorCalendar(doctorUUID, calendarUUID, medicalUniteUUID));
	}

	@PatchMapping(value = "/{calendarUUID}/medical-unites/{medicalUniteUUID}")
	public ResponseEntity<CalendarResponseDTO> editCalendarMedicalUnite(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID medicalUniteUUID) {
		calendarServiceImpl.editCalendarMedicalUnite(doctorUUID, calendarUUID, medicalUniteUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{calendarUUID}/medical-unites/{medicalUniteUUID}")
	public ResponseEntity<CalendarResponseDTO> deleteMedicalUniteFromCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID medicalUniteUUID) {
		calendarServiceImpl.removeMedicalUniteFromCalendar(doctorUUID, calendarUUID, medicalUniteUUID);
		return ResponseEntity.ok().build();
	}
}
