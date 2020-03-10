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
public class CalendarController {

	final private CalendarService calendarService;

	public CalendarController(
			CalendarService calendarService){
		this.calendarService = calendarService;
	}

	@GetMapping
	public ResponseEntity<List<CalendarResponseDTO>> getDoctorCalendars(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(calendarService.getDoctorCalendars(doctorUUID));
	}

	@PostMapping
	public ResponseEntity<CalendarResponseDTO> add(
			@Valid @RequestBody AddEditCalendarDTO addEditCalendarDTO,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(addEditCalendarDTO, doctorUUID));
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
}
