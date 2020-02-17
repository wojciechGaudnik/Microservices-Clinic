package com.clinics.doctors.repositorie;

import com.clinics.doctors.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
