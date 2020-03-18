package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.doctors.data.repositorie.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class DoctorMedicalUnitClient {

	final private DoctorRepository doctorRepository;
	final private DoctorService doctorService;
	final private RestTemplate restTemplate;

	public DoctorMedicalUnitClient(
			DoctorRepository doctorRepository,
			DoctorService doctorService,
			RestTemplate restTemplate) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
		this.restTemplate = restTemplate;
	}

	public List<MedicalUnitResponseDTO> getAll(UUID doctorUUID) {
		return doctorService
				.getByUUID(doctorUUID)
				.getMedicalUnitsUUID()
				.stream()
				.map(this::getMedicalUnitResponseDTO)
				.collect(Collectors.toList());
	}

	public MedicalUnitResponseDTO get(UUID doctorUUID, UUID medicalUnitUUID) {
		if (doctorService
				.getByUUID(doctorUUID)
				.getMedicalUnitsUUID()
				.stream()
				.noneMatch(uuid -> uuid.equals(medicalUnitUUID))) {
			throw new NoSuchElementException("Doctor doesn't have such medical unit");
		}
		return getMedicalUnitResponseDTO(medicalUnitUUID);
	}

	public MedicalUnitResponseDTO save(UUID medicalUnitUUID, UUID doctorUUID) {
		var medicalUnit = getMedicalUnitResponseDTO(medicalUnitUUID);
		var doctor = doctorService.getByUUID(doctorUUID);
		var doctorMedicalUnites = doctor.getMedicalUnitsUUID();
		if (doctorMedicalUnites.stream().anyMatch(uuid -> uuid.equals(medicalUnitUUID))) {
			throw new ValidationException("Doctor already have such medical unit");
		}
		doctorMedicalUnites.add(medicalUnitUUID);
		doctorRepository.save(doctor);
		return medicalUnit;
	}

	public void delete(UUID doctorUUID, UUID medicalUnitUUID) {
		getMedicalUnitResponseDTO(medicalUnitUUID);
		var doctor = doctorService.getByUUID(doctorUUID);
		var doctorMedicalUnites = doctor.getMedicalUnitsUUID();
		if (doctorMedicalUnites.stream().noneMatch(uuid -> uuid.equals(medicalUnitUUID))) {
			throw new ValidationException("Doctor doesn't have such medical unit");
		}
		doctorMedicalUnites.remove(medicalUnitUUID);
		doctorRepository.save(doctor);
	}

	public MedicalUnitResponseDTO getMedicalUnitResponseDTO(UUID medicalUnitUUID) {
		var medicalUnit = restTemplate
				.getForObject("http://medical-units-mssc/medical-units/" + medicalUnitUUID,
						MedicalUnitResponseDTO.class);
		if(medicalUnit == null)
			throw new NoSuchElementException("No such medical unit");
		return medicalUnit;
	}
}
