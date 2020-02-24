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

import javax.persistence.EntityManager;
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
	@Lazy
	@Autowired
	private PhotoRepository photoRepository;
	@Lazy
	@Autowired
	private AlbumRepository albumRepository;
	@Lazy
	@Autowired
	private EntityManager entityManager;


//	@Transactional
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
//		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));

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
//		calendarRepository.saveAll(Arrays.asList(
//				calendarPediatric,
//				calendarGP,
//				calendarNeurologist,
//				calendarCardiologist,
//				calendarGynecologist,
//				calendarUrologist));

		Doctor doctor1 = Doctor
				.builder()
				.doctoruuid(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Ola")
				.lastName("Olkowska")
				.photoUrl("http://ola.pl")
				.licence("Licence example doctor 1")
				.build();
		Doctor doctor2 = Doctor
				.builder()
				.doctoruuid(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
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

//		Album myAlbum = new Album();
//		Photo photo1 = new Photo();
//		Photo photo2 = new Photo();
//
//		photo1.setAlbum(myAlbum);
//		photo2.setAlbum(myAlbum);
//		albumRepository.save(myAlbum);
//		photoRepository.save(photo1);


//		calendarGP.setDoctor(doctor1);
//		calendarPediatric.setDoctor(doctor1);
//		doctorRepository.save(doctor1);
//		calendarRepository.saveAll(Arrays.asList(calendarGP, calendarPediatric));

//		doctor1 = doctorRepository.findByDoctoruuid(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9")).get();
		specializationGP.setDoctors(new ArrayList<>());
		specializationGP.getDoctors().add(doctor1);
		specializationGP.getDoctors().add(doctor2);
		for (var one : specializationGP.getDoctors()) {
			log.warn(one.getFirstName());
		}
		doctorRepository.saveAll(Arrays.asList( doctor2, doctor1));
		specializationRepository.save(specializationGP);
//		calendarRepository.saveAll(Arrays.asList(calendarGP, calendarPediatric));
//		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2));
//		entityManager.getTransaction().begin();
//		entityManager.persist(specializationGP);
//		entityManager.getTransaction().commit();



//		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));

//		calendarGP.get
//		doctor1.getCalendars().add(calendarGP);
//		doctorRepository.save(doctor1);
//		calendarRepository.save(calendarGP);

//		doctor1.getCalendars().add(calendarGP);
//		doctor1.getCalendars().add(calendarNeurologist);
//		doctor1.getCalendars().add(calendarPediatric);
//		doctor1.getSpecializations().add(specializationGP);
//		doctor1.getSpecializations().add(specializationPediatric);
//		doctorRepository.save(doctor1);



//		calendarGP.setDoctor(doctor1);
//		calendarNeurologist.setDoctor(doctor1);
//		calendarPediatric.setDoctor(doctor1);
//		calendarRepository.saveAll(Arrays.asList(
//				calendarGP,
//				calendarNeurologist,
//				calendarPediatric));
//		doctorRepository.save(doctor1);
//		calendarRepository.saveAll(Arrays.asList(
//				calendarPediatric,
//				calendarGP,
//				calendarNeurologist));

		//	todo https://stackoverflow.com/questions/3927091/save-child-objects-automatically-using-jpa-hibernate
//		specializationGP.getDoctors().addAll(Arrays.asList(doctor1, doctor2, doctor3)); // todo BUG doesn't work

//		calendarRepository.saveAll(Arrays.asList(
//				calendarPediatric,
//				calendarGP,
//				calendarNeurologist,
//				calendarCardiologist,
//				calendarGynecologist,
//				calendarUrologist));
//		doctorRepository.save(doctor2);
//		doctorRepository.save(doctor3);
//		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));

		System.out.println(ConsoleColors.GREEN_BOLD);
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

		System.out.println(ConsoleColors.RESET);
	}
}
