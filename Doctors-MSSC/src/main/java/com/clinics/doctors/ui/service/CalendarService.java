package com.clinics.doctors.ui.service;

import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.repositorie.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Slf4j
@Service
public class CalendarService {

	final private CalendarRepository calendarRepository;

	public CalendarService(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}

	public List<Calendar> getDoctorCalendars(UUID uuid) {
		return calendarRepository.getAllByDoctor_Doctoruuid(uuid);
	}
}
