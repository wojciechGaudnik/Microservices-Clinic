package com.clinics.doctors.repositories;

import com.clinics.doctors.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
