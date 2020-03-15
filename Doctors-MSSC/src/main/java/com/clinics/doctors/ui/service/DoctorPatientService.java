package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.response.outer.PatientResponseDTO;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DoctorPatientService {

	final private RestTemplate restTemplate;
	final private DoctorRepository doctorRepository;
	final private DoctorService doctorService;

	public DoctorPatientService(RestTemplate restTemplate, DoctorRepository doctorRepository, DoctorService doctorService) {
		this.restTemplate = restTemplate;
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
	}

	public List<PatientResponseDTO> getAll(UUID doctorUUID) {
		return doctorService
				.getDoctor(doctorUUID)
				.getPatientsUUID()
				.stream()
				.map(this::getPatientResponseDTO)
				.collect(Collectors.toList());
	}

	public PatientResponseDTO get(UUID doctorUUID, UUID patientUUID) {
		if (doctorService
				.getDoctor(doctorUUID)
				.getPatientsUUID()
				.stream()
				.noneMatch(uuid -> uuid.equals(patientUUID))) {
			throw new NoSuchElementException("Doctor doesn't have such patient");
		}
//		try{
//
//		} catch ()
		return getPatientResponseDTO(patientUUID);
	}

	public PatientResponseDTO save(UUID patientUUID, UUID doctorUUID) {
		var medicalUnit = getPatientResponseDTO(patientUUID);
		var doctor = doctorService.getDoctor(doctorUUID);
		var doctorPatients = doctor.getPatientsUUID();
		if (doctorPatients.stream().anyMatch(uuid -> uuid.equals(patientUUID))) {
			throw new ValidationException("Doctor already have such patient");
		}
		doctorPatients.add(patientUUID);
		doctorRepository.save(doctor);
		return medicalUnit;
	}

	public void delete(UUID doctorUUID, UUID patientUUID) {
		getPatientResponseDTO(patientUUID);
		var doctor = doctorService.getDoctor(doctorUUID);
		var doctorPatients = doctor.getPatientsUUID();
		if (doctorPatients.stream().noneMatch(uuid -> uuid.equals(patientUUID))) {
			throw new ValidationException("Doctor doesn't have such patient");
		}
		doctorPatients.remove(patientUUID);
		doctorRepository.save(doctor);
	}

	private PatientResponseDTO getPatientResponseDTO(UUID patientUUID) {
		return restTemplate
				.getForObject("http://patient-mssc/patients/" + patientUUID,
						PatientResponseDTO.class);
	}
}
