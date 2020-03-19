package com.clinics.doctors.ui.service.JPAimpl;

import com.clinics.common.DTO.request.outer.doctor.SpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
import com.clinics.doctors.data.model.Specialization;
import com.clinics.doctors.data.repositorie.DoctorRepository;
import com.clinics.doctors.data.repositorie.SpecializationRepository;
import com.clinics.doctors.ui.service.JPAimpl.DoctorServiceImpl;
import com.clinics.doctors.ui.service.SpecializationService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class SpecializationServiceImpl implements SpecializationService {

	final private SpecializationRepository specializationRepository;
	final private DoctorRepository doctorRepository;
	final private DoctorServiceImpl doctorServiceImpl;
	final private ModelMapper modelMapper;

	public SpecializationServiceImpl(
			SpecializationRepository specializationRepository,
			DoctorRepository doctorRepository,
			DoctorServiceImpl doctorServiceImpl,
			ModelMapper modelMapper) {
		this.specializationRepository = specializationRepository;
		this.doctorRepository = doctorRepository;
		this.doctorServiceImpl = doctorServiceImpl;
		this.modelMapper = modelMapper;

	}

	public List<SpecializationResponseDTO> getAllSpecializations() {
		return specializationRepository
				.findAll()
				.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponseDTO.class))
				.collect(Collectors.toList());
	}

	public List<SpecializationResponseDTO> getDoctorSpecializations(UUID doctorUUID) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		return specializationRepository.findAllByDoctors(doctor)
				.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponseDTO.class))
				.collect(Collectors.toList());
	}

	public SpecializationResponseDTO getDoctorSpecialization(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		if (doctor.getSpecializations().stream().noneMatch(specialization -> specialization.getSpecializationUUID().equals(specializationUUID))) {
			throw new NoSuchElementException(String.format("Doctor doesn't have such specialization %s", specializationUUID ));
		}
		var specialization = getSpecialization(specializationUUID);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO getSpecializationByUUID(UUID specializationUUID) {
		var specialization = getSpecialization(specializationUUID);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO saveSpecialization(SpecializationDTO specializationDTO) {
		var specialization = modelMapper.map(specializationDTO, Specialization.class);
		specialization.setSpecializationUUID(UUID.randomUUID());
		return modelMapper.map(specializationRepository.save(specialization), SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO saveSpecializationIntoDoctor(UUID doctorUUID, SpecializationDTO specializationDTO) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		if (specializationRepository.existsByName(specializationDTO.getName())) {
			if (doctor.getSpecializations().stream().anyMatch(s -> s.getName().equals(specializationDTO.getName()))) {
				throw new DataIntegrityViolationException("Doctor already have such specialization");
			}
			var specialization = specializationRepository.findByName(specializationDTO.getName());
			doctor.getSpecializations().add(specialization);
			doctorRepository.save(doctor);
			return modelMapper.map(specialization, SpecializationResponseDTO.class);
		}
		var specialization = modelMapper.map(specializationDTO, Specialization.class);
		specialization.setSpecializationUUID(UUID.randomUUID());
		doctor.getSpecializations().add(specialization);
		specializationRepository.save(specialization);
		doctorRepository.save(doctor);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO saveExistingSpecializationIntoDoctor(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		var optionalSpecialization = specializationRepository.findBySpecializationUUID(specializationUUID);
		if (optionalSpecialization.isEmpty()) {
			throw new NoSuchElementException("No such specialization");
		}
		var specialization = optionalSpecialization.get();
		if (doctor.getSpecializations().stream().anyMatch(s -> s.getName().equals(specialization.getName()))) {
			throw new DataIntegrityViolationException("Doctor already have such specialization");
		}
		doctor.getSpecializations().add(specialization);
		doctorRepository.save(doctor);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public void editSpecialization(SpecializationDTO specializationDTO, UUID specializationUUID) {
		var specialization = getSpecialization(specializationUUID);
		modelMapper.map(specializationDTO, specialization);
		specializationRepository.save(specialization);
	}

	public void removeSpecializationFromDoctor(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorServiceImpl.getByUUID(doctorUUID);
		var specialization = getSpecialization(specializationUUID);
		doctor.getSpecializations().remove(specialization);
		doctorRepository.save(doctor);
	}

	public void deleteSpecialization(UUID specializationUUID) {
		var specialization = getSpecialization(specializationUUID);
		specializationRepository.delete(specialization);
	}

	private Specialization getSpecialization(UUID specializationUUID) {
		var optionalSpecialization = specializationRepository.findBySpecializationUUID(specializationUUID);
		if (optionalSpecialization.isEmpty()) {
			throw new NoSuchElementException(String.format("No such specialization in system %s", specializationUUID ));
		}
		return optionalSpecialization.get();
	}
}
