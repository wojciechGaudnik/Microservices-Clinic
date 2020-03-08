package com.clinics.doctors.ui.controller;

import com.clinics.common.DTO.request.outer.AddEditCalendarDTO;
import com.clinics.common.DTO.response.outer.CalendarResponseDTO;
import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/calendars")
public class CalendarController {

	final private CalendarService calendarService;

	public CalendarController(
			CalendarService calendarService){
		this.calendarService = calendarService;
	}

	@GetMapping
	public ResponseEntity<List<Calendar>> getDoctorCalendars(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(calendarService.getDoctorCalendars(doctorUUID));
	}

	@PostMapping
	public ResponseEntity<CalendarResponseDTO> addCalendar(
			@Valid @RequestBody AddEditCalendarDTO addEditCalendarDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(201).body(calendarService.save(addEditCalendarDTO, doctorUUID));
	}

	@PatchMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> editCalendar(
			@Valid @RequestBody AddEditCalendarDTO addEditCalendarDTO,
			@PathVariable UUID calendarUUID) {
		calendarService.edit(addEditCalendarDTO, calendarUUID);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/{calendarUUID}")
	public ResponseEntity<Void> delCalendar(
			@PathVariable UUID calendarUUID) {
		log.error("---> 1 <---");
		calendarService.delete(calendarUUID);
		log.error("---> 2 <---");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
