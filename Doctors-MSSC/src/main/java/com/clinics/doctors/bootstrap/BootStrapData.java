package com.clinics.doctors.bootstrap;


import com.clinics.common.ConsoleColors;
import com.clinics.doctors.models.Doctor;
import com.clinics.doctors.models.Calendar;
import com.clinics.doctors.models.Specialization;
import com.clinics.doctors.repositories.CalendarRepository;
import com.clinics.doctors.repositories.DoctorRepository;
import com.clinics.doctors.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootStrapData implements CommandLineRunner {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private CalendarRepository calendarRepository;
	@Autowired
	private SpecializationRepository specializationRepository;


	@Override
	public void run(String... args) throws Exception {

		Specialization specializationPediatric = Specialization
				.builder()
				.name("Pediatric Specialization")
				.build();

		Specialization specializationGP = Specialization
				.builder()
				.name("GP Specialization")
				.build();

		Calendar calendarPediatric = Calendar
				.builder()
				.name("Pediatric Calendar")
				.build();

		Calendar calendarGP = Calendar
				.builder()
				.name("GP Calendar")
				.build();

		Calendar calendarNeurologist = Calendar
				.builder()
				.name("Neurologist Calendar")
				.build();

		Calendar calendarCardiologist = Calendar
				.builder()
				.name("Cardiologist Calendar")
				.build();

		Calendar calendarUrologist = Calendar
				.builder()
				.name("Urologist Calendar")
				.build();

		Calendar calendarGynecologist = Calendar
				.builder()
				.name("Gynecologist Calendar")
				.build();

		Doctor doctor1 = Doctor
				.builder()
				.name("doctor1")
				.uuid(UUID.randomUUID())
				.licence("Licence example")
				.build();
		Doctor doctor2 = Doctor
				.builder()
				.name("doctor2")
				.uuid(UUID.randomUUID())
				.licence("Licence example")
				.build();
		Doctor doctor3 = Doctor
				.builder()
				.name("doctor3")
				.uuid(UUID.randomUUID())
				.licence("Licence example")
				.build();

		calendarGP.setDoctor(doctor1);
		calendarNeurologist.setDoctor(doctor1);
		calendarPediatric.setDoctor(doctor1);

		specializationGP.setDoctors(Arrays.asList(doctor1, doctor2, doctor3)); // todo BUG doesn't work

		calendarRepository.saveAll(Arrays.asList(
				calendarPediatric,
				calendarGP,
				calendarNeurologist,
				calendarCardiologist,
				calendarGynecologist,
				calendarUrologist));
		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));
		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));

		System.out.println(ConsoleColors.GREEN_BOLD);
		var doctor1After = doctorRepository.findById(1L).get();
		for (Calendar one : doctor1After.getCalendars()) {
			System.out.println("---> " + one.getName() + " <---");
		}

		var calendarGPAfter = calendarRepository.findById(1L).get();
		System.out.println("---> " + calendarGPAfter.getName() + " <---");
		System.out.println("---> " + calendarGPAfter.getDoctor().getName() + " <---");

		List<Calendar> calendars = Arrays.asList(calendarCardiologist, calendarGynecologist, calendarUrologist);
		Doctor doctorToChange = doctorRepository.findById(2L).get();
		doctorToChange.setCalendars(calendars);  //todo BUG doesn't work
		doctorRepository.save(doctorToChange);



		System.out.println(ConsoleColors.RESET);
	}
}
