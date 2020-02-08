package com.clinics.doctors.repositories;

import com.clinics.doctors.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
//public interface CalendarRepository extends CrudRepository<Calendar, UUID> {
}
