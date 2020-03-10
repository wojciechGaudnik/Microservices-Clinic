package com.clinics.patient.bootstrap;

import com.clinics.common.patient.VisitStatus;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;
import com.clinics.patient.repository.PatientRepository;
import com.clinics.patient.repository.VisitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
		Patient patient1 = Patient
				.builder()
				.uuid(UUID.randomUUID())
				.firstName("Maciej")
				.lastName("Maciejkowski")
				.photoUrl("http://maciej.pl")
				.pesel("89103005444")
				.build();

		Visit visit1 = Visit
				.builder()
				.date(new Date())
				.description("Tralala")
				.doctorUUID(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.status(VisitStatus.NEW)
				.uuid(UUID.randomUUID())
				.build();

		patientRepository.save(patient1);
		visit1.setPatient(patient1);
		visitRepository.save(visit1);
	}
}
