package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.doctor.AddEditCalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.ui.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/calendars")
public class DoctorCalendarController {

	final private CalendarService calendarService;

	public DoctorCalendarController(
			CalendarService calendarService){
		this.calendarService = calendarService;
	}

	@GetMapping
	public ResponseEntity<List<CalendarResponseDTO>> getDoctorCalendars(
			@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(calendarService.getDoctorCalendars(doctorUUID));
	}

	@GetMapping(value = "/{calendarUUID}")
	public ResponseEntity<CalendarResponseDTO> getDoctorCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID){
		return ResponseEntity.ok().body(calendarService.getDoctorCalendar(doctorUUID, calendarUUID));
	}

	@PostMapping
	public ResponseEntity<CalendarResponseDTO> add(
			@Valid @RequestBody AddEditCalendarDTO addEditCalendarDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(addEditCalendarDTO, doctorUUID));
	}

	@PostMapping(value = "/{calendarUUID}/medical-unites/{medicalUniteUUID}")
	public ResponseEntity<CalendarResponseDTO> addMedicalUniteIntoCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID medicalUniteUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(doctorUUID, calendarUUID, medicalUniteUUID));
	}

	@PatchMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> edit(
			@Valid @RequestBody AddEditCalendarDTO addEditCalendarDTO,
			@PathVariable UUID calendarUUID) {
		calendarService.edit(addEditCalendarDTO, calendarUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> del(
			@PathVariable UUID calendarUUID) {
		calendarService.delete(calendarUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{calendarUUID}/medical-unites/{medicalUniteUUID}")
	public ResponseEntity<CalendarResponseDTO> deleteMedicalUniteFromCalendar(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID calendarUUID,
			@PathVariable UUID medicalUniteUUID) {
		calendarService.delete(doctorUUID, calendarUUID, medicalUniteUUID);
		return ResponseEntity.ok().build();
	}
}
