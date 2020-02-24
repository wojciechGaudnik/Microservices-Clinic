package com.clinics.doctors.bootstrap;

import com.clinics.common.ConsoleColors;
import com.clinics.doctors.model.*;
import com.clinics.doctors.model.Calendar;
import com.clinics.doctors.repositorie.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
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

	@Transactional
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
				.doctoruuid(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.firstName("Ola")
				.lastName("Olkowska")
				.photoUrl("http://ola.pl")
				.licence("Licence example doctor 1")
				.build();
		Doctor doctor2 = Doctor
				.builder()
				.doctoruuid(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))
				.firstName("Ala")
				.lastName("Alowsla")
				.photoUrl("http://ala.pl")
				.licence("Licence example doctor 2")
				.build();
		Doctor doctor3 = Doctor
				.builder()
				.doctoruuid(UUID.randomUUID())  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Ela")
				.lastName("Elkowska")
				.photoUrl("http://ela.pl")
				.licence("Licence example doctor 3")
				.build();


		calendarGP.setDoctor(doctor1);
		calendarPediatric.setDoctor(doctor1);
		calendarCardiologist.setDoctor(doctor1);
		calendarGynecologist.setDoctor(doctor2);
		calendarNeurologist.setDoctor(doctor2);
		calendarUrologist.setDoctor(doctor3);

		specializationGP.getDoctors().add(doctor1);
		specializationGP.getDoctors().add(doctor2);
		specializationPediatric.getDoctors().add(doctor1);

		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));
		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));
		calendarRepository.saveAll(Arrays.asList(
				calendarGP,
				calendarPediatric,
				calendarCardiologist,
				calendarGynecologist,
				calendarNeurologist,
				calendarUrologist));

//	todo https://stackoverflow.com/questions/3927091/save-child-objects-automatically-using-jpa-hibernate
//		specializationGP.getDoctors().addAll(Arrays.asList(doctor1, doctor2, doctor3)); // todo BUG doesn't work

		var doctorsAfter = doctorRepository.findAll();
		var calendars1After = calendarRepository.findAll();
		var specializationsAfter = specializationRepository.findAll();

		System.out.println(ConsoleColors.GREEN_BOLD);

		System.out.println("-------------------------------------------------------");
		doctorsAfter.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		calendars1After.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		specializationsAfter.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");

		System.out.println(ConsoleColors.RESET);
	}
}
