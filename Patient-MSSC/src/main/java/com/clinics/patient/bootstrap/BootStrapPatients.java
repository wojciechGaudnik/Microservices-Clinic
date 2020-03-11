package com.clinics.patient.bootstrap;

import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class BootStrapPatients implements CommandLineRunner {

	private final PatientRepository patientRepository;
	private final VisitRepository visitRepository;

	public BootStrapPatients(PatientRepository patientRepository, VisitRepository visitRepository) {
		this.patientRepository = patientRepository;
		this.visitRepository = visitRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Visit visit1 = Visit
				.builder()
				.date(new Date())
				.description("Tralala")
				.doctorUUID(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.status(VisitStatus.NEW)
				.uuid(UUID.randomUUID())
				.build();

		Patient patient1 = Patient
				.builder()
				.patientUUID(UUID.fromString("11f0f891-b243-4547-803b-605f72b11b11"))
				.firstName("Maciej")
				.lastName("Maciejkowski")
				.photoUrl("http://maciej.pl")
				.pesel("89103005444")
				.visits(Collections.singletonList(visit1))
				.build();

		visit1.setPatient(patient1);
		patientRepository.save(patient1);
	}
}
