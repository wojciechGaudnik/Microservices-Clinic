package com.clinics.patient.bootstrap;

import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class BootStrapPatients implements CommandLineRunner {

	private final PatientRepository patientRepository;

	public BootStrapPatients(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public void run(String... args) {

		Visit visit1 = Visit
				.builder()
				.date(LocalDateTime.now())
				.description("Visit1")
				.doctorUUID(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.status(VisitStatus.NEW)
				.visitUUID(UUID.randomUUID())
				.build();

		Visit visit2 = Visit
				.builder()
				.date(LocalDateTime.now())
				.description("Visit2")
				.doctorUUID(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))
				.status(VisitStatus.FINISHED)
				.visitUUID(UUID.randomUUID())
				.build();

		Visit visit3 = Visit
				.builder()
				.date(LocalDateTime.now())
				.description("Visit3")
				.doctorUUID(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))
				.status(VisitStatus.NEW)
				.visitUUID(UUID.randomUUID())
				.build();

		Patient patient1 = Patient
				.builder()
				.patientUUID(UUID.fromString("11f0f891-b243-4547-803b-605f72b11b11"))
				.firstName("Maciej")
				.lastName("Maciejkowski")
				.photoUrl("http://maciej.pl")
				.pesel("89103005444")
				.visits(Arrays.asList(visit1, visit2, visit3))
				.build();

		visit1.setPatient(patient1);
		visit2.setPatient(patient1);
		visit3.setPatient(patient1);
		patientRepository.save(patient1);
	}
}
