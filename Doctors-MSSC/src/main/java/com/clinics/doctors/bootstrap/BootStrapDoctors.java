package com.clinics.doctors.bootstrap;


import com.clinics.common.ConsoleColors;
import com.clinics.doctors.model.Doctor;
import com.clinics.doctors.model.Calendar;
import com.clinics.doctors.model.Specialization;
import com.clinics.doctors.repositorie.CalendarRepository;
import com.clinics.doctors.repositorie.DoctorRepository;
import com.clinics.doctors.repositorie.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootStrapDoctors implements CommandLineRunner {

	@Lazy
	@Autowired
	private DoctorRepository doctorRepository;
	@Lazy
	@Autowired
	private CalendarRepository calendarRepository;
	@Lazy
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
				.doctor_uuid(UUID.randomUUID())  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Jan")
				.lastName("Janjanowski")
				.photoUrl("http://jan.pl")
				.licence("Licence example doctor 1")
				.build();
		Doctor doctor2 = Doctor
				.builder()
				.doctor_uuid(UUID.randomUUID())  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Adam")
				.lastName("Adamowski")
				.photoUrl("http://adam.pl")
				.licence("Licence example doctor 2")
				.build();
		Doctor doctor3 = Doctor
				.builder()
				.doctor_uuid(UUID.randomUUID())  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Ola")
				.lastName("Olkowska")
				.photoUrl("http://ola.pl")
				.licence("Licence example doctor 3")
				.build();

		calendarGP.setDoctor(doctor1);
		calendarNeurologist.setDoctor(doctor1);
		calendarPediatric.setDoctor(doctor1);

		//	todo https://stackoverflow.com/questions/3927091/save-child-objects-automatically-using-jpa-hibernate
		specializationGP.getDoctors().addAll(Arrays.asList(doctor1, doctor2, doctor3)); // todo BUG doesn't work

		calendarRepository.saveAll(Arrays.asList(
				calendarPediatric,
				calendarGP,
				calendarNeurologist,
				calendarCardiologist,
				calendarGynecologist,
				calendarUrologist));
		doctorRepository.save(doctor2);
		doctorRepository.save(doctor3);
//		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));
//		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));
//
//		System.out.println(ConsoleColors.GREEN_BOLD);
//		var doctor1After = doctorRepository.findById(1L).get();
//		for (Calendar one : doctor1After.getCalendars()) {
//			System.out.println("---> " + one.getName() + " <---");
//		}
//
//		var calendarGPAfter = calendarRepository.findById(1L).get();
//		System.out.println("---> " + calendarGPAfter.getName() + " <---");
//		System.out.println("---> " + calendarGPAfter.getDoctor().getLicence() + " <---");
//
//		List<Calendar> calendars = Arrays.asList(calendarCardiologist, calendarGynecologist, calendarUrologist);
//		Doctor doctorToChange = doctorRepository.findById(2L).get();
//		doctorToChange.getCalendars().addAll(calendars);  //todo BUG doesn't work
//		doctorRepository.save(doctorToChange);
//
//		System.out.println(ConsoleColors.RESET);
	}
}
