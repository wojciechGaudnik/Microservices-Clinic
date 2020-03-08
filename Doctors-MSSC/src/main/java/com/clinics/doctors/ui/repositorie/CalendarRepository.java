package com.clinics.doctors.ui.repositorie;

import com.clinics.doctors.ui.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	List<Calendar> getAllByDoctor_Doctoruuid(UUID uuid);
	Optional<Calendar> getCalendarByCalendaruuid(UUID uuid);
	void deleteByCalendaruuid(UUID uuid);
}
