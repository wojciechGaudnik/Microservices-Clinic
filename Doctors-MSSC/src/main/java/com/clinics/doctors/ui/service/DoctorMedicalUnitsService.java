package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.medicalUnit.AddMedicalUnitUUID_DTO;
import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;

@Slf4j
@Transactional
@Service
public class DoctorMedicalUnitsService {

	final private RestTemplate restTemplate;
	final private DoctorRepository doctorRepository;

	public DoctorMedicalUnitsService(RestTemplate restTemplate, DoctorRepository doctorRepository) {
		this.restTemplate = restTemplate;
		this.doctorRepository = doctorRepository;
	}

	public List<MedicalUnitResponseDTO> getAll(UUID doctorUUID) {
		var optionalDoctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if (optionalDoctor.isEmpty()) {
			throw new NoSuchElementException("No such doctor in system");
		}
		var doctorsMedicalUnites = optionalDoctor.get().getMedicalUnitsUUID();
		List<MedicalUnitResponseDTO> medicalUnitResponseDTOList = new LinkedList<>();
		for (UUID medicalUnitUUID: doctorsMedicalUnites) {
			medicalUnitResponseDTOList.add(getMedicalUnite(medicalUnitUUID));
		}
		return medicalUnitResponseDTOList;
	}

	public MedicalUnitResponseDTO get(UUID doctorUUID, UUID medicalUnitUUID) {
		var optionalDoctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if (optionalDoctor.isEmpty()) {
			throw new NoSuchElementException("No such doctor in system");
		}
		if (optionalDoctor.get().getMedicalUnitsUUID().stream().noneMatch(uuid -> uuid.equals(medicalUnitUUID))) {
			throw new NoSuchElementException("Doctor doesn't have such medical unit");
		}
		return getMedicalUnite(medicalUnitUUID);
	}

	public MedicalUnitResponseDTO save(AddMedicalUnitUUID_DTO addMedicalUnitUUIDDTO, UUID doctorUUID) {
		var medicalUnit = getMedicalUnite(addMedicalUnitUUIDDTO.getMedicalUnitUUID());
		if(medicalUnit == null)
			throw new NoSuchElementException("No such medical unit");
		var doctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if(doctor.isEmpty())
			throw new NoSuchElementException("No such doctor in system");
		var doctorMedicalUnites = doctor.get().getMedicalUnitsUUID();
		if (doctorMedicalUnites.stream().anyMatch(uuid -> uuid.equals(addMedicalUnitUUIDDTO.getMedicalUnitUUID()))) {
			throw new ValidationException("Doctor already have such medical unit");
		}
		doctorMedicalUnites.add(addMedicalUnitUUIDDTO.getMedicalUnitUUID());
		doctorRepository.save(doctor.get());
		return medicalUnit;
	}

	public void delete(UUID doctorUUID, UUID medicalUnitUUID) {
		var medicalUnit = getMedicalUnite(medicalUnitUUID);
		if(medicalUnit == null)
			throw new NoSuchElementException("No such medical unit");
		var doctor = doctorRepository.findByDoctorUUID(doctorUUID);
		if(doctor.isEmpty())
			throw new NoSuchElementException("No such doctor in system");
		var doctorMedicalUnites = doctor.get().getMedicalUnitsUUID();
		if (doctorMedicalUnites.stream().noneMatch(uuid -> uuid.equals(medicalUnitUUID))) {
			throw new ValidationException("Doctor doesn't have such medical unit");
		}
		doctorMedicalUnites.remove(medicalUnitUUID);
		doctorRepository.save(doctor.get());
	}

	private MedicalUnitResponseDTO getMedicalUnite(UUID medicalUnitUUID) {
		return restTemplate
				.getForObject("http://medical-units-mssc/medical-units/" + medicalUnitUUID,
						MedicalUnitResponseDTO.class);
	}
}
