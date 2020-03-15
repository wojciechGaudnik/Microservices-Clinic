package com.clinics.medicalunits.bootstrap;

import com.clinics.medicalunits.ui.model.MedicalUnit;
import com.clinics.medicalunits.ui.repositorie.MedicalUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class BootStrapMedicalUnits implements CommandLineRunner {

	private final MedicalUnitRepository medicalUnitRepository;

	public BootStrapMedicalUnits(MedicalUnitRepository medicalUnitRepository) {
		this.medicalUnitRepository = medicalUnitRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		var medicalUnit1 = MedicalUnit
				.builder()
				.medicalUnitUUID(UUID.fromString("17d25fda-fece-4294-9adb-34037445ca77"))
				.name("Medical Unit Clinic")
				.address("ul. zbożowa 55/55 44-333 Żytowo")
				.doctorsUUID(Arrays.asList(
						UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"),
						UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d")
				))
				.build();

		var medicalUnit2 = MedicalUnit
				.builder()
				.medicalUnitUUID(UUID.fromString("2d43f0c5-3338-46fc-8a44-9e50e20c256d"))
				.name("Medical Unit Pharmacy")
				.address("ul. ryżowa 22/22 66-111 Rzezawa")
				.build();

		var medicalUnit3 = MedicalUnit
				.builder()
				.medicalUnitUUID(UUID.fromString("0b18f17d-098a-404d-a949-4b1ad394d43a"))
				.name("Medical Unit Therapist")
				.address("ul. kukurydziana 11/33 77-222 Jim Beam ")
				.build();

		medicalUnitRepository.saveAll(Arrays.asList(medicalUnit1, medicalUnit2, medicalUnit3));
	}
}
