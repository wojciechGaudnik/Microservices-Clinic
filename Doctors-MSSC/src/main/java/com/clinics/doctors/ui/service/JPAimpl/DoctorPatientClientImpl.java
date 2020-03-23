package com.clinics.doctors.ui.service.JPAimpl;

import com.clinics.common.DTO.response.outer.PatientResponseDTO;
import com.clinics.doctors.data.repositorie.DoctorRepository;
import com.clinics.doctors.ui.service.DoctorPatientClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class DoctorPatientClientImpl implements DoctorPatientClient {

	final private DoctorRepository doctorRepository;
	final private DoctorServiceImpl doctorServiceImpl;
	final private RestTemplate restTemplate;

	public DoctorPatientClientImpl(
			DoctorRepository doctorRepository,
			DoctorServiceImpl doctorServiceImpl,
			RestTemplate restTemplate) {
		this.doctorRepository = doctorRepository;
		this.doctorServiceImpl = doctorServiceImpl;
		this.restTemplate = restTemplate;
	}

	public List<PatientResponseDTO> getAll(UUID doctorUUID) {
		return doctorServiceImpl
				.getByUUID(doctorUUID)
				.getPatientsUUID()
				.stream()
				.map(this::getPatientResponseDTO)
				.collect(Collectors.toList());
	}

	public PatientResponseDTO get(UUID doctorUUID, UUID patientUUID) {
		if (doctorServiceImpl
				.getByUUID(doctorUUID)
				.getPatientsUUID()
				.stream()
				.noneMatch(uuid -> uuid.equals(patientUUID))) {
			throw new NoSuchElementException("Doctor doesn't have such patient");
		}
		return getPatientResponseDTO(patientUUID);
	}

	public PatientResponseDTO save(UUID patientUUID, UUID doctorUUID) {
		var medicalUnit = getPatientResponseDTO(patientUUID);
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
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
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		var doctorPatients = doctor.getPatientsUUID();
		if (doctorPatients.stream().noneMatch(uuid -> uuid.equals(patientUUID))) {
			throw new ValidationException("Doctor doesn't have such patient");
		}
		doctorPatients.remove(patientUUID);
		doctorRepository.save(doctor);
	}

	private PatientResponseDTO getPatientResponseDTO(UUID patientUUID) {
		var patient = restTemplate
				.getForObject("http://patient-mssc/patients/" + patientUUID,
						PatientResponseDTO.class);
		if (patient == null) {
			throw new NoSuchElementException("No such patient");
		}
		return patient;
	}
}
