package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.AddEditSpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
import com.clinics.doctors.ui.model.Specialization;
import com.clinics.doctors.ui.repositorie.DoctorRepository;
import com.clinics.doctors.ui.repositorie.SpecializationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class SpecializationService {

	final private SpecializationRepository specializationRepository;
	final private ModelMapper modelMapper;
	final private DoctorService doctorService;

	public SpecializationService(
			SpecializationRepository specializationRepository,
			ModelMapper modelMapper,
			DoctorService doctorService) {
		this.specializationRepository = specializationRepository;
		this.modelMapper = modelMapper;
		this.doctorService = doctorService;
	}

	public List<SpecializationResponseDTO> getAll() {
		return specializationRepository
				.findAll()
				.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponseDTO.class))
				.collect(Collectors.toList());
	}

	public SpecializationResponseDTO getByUUID(UUID specializationUUID) {
		Specialization specialization = getSpecialization(specializationUUID);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO save(AddEditSpecializationDTO addEditSpecializationDTO) {
		Specialization specialization = modelMapper.map(addEditSpecializationDTO, Specialization.class);
		specialization.setSpecializationUUID(UUID.randomUUID());
		return modelMapper.map(specializationRepository.save(specialization), SpecializationResponseDTO.class);
	}

	public void edit(AddEditSpecializationDTO addEditSpecializationDTO, UUID specializationUUID) {

	}

	public void delete(UUID specializationUUID) {

	}

	public List<SpecializationResponseDTO> getDoctorSpecializations(UUID doctorUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		return specializationRepository.findAllByDoctors(doctor)
				.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponseDTO.class))
				.collect(Collectors.toList());
	}

	public SpecializationResponseDTO getDoctorSpecialization(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorService.getDoctor(doctorUUID);
		if (doctor.getSpecializations().stream().noneMatch(specialization -> specialization.getSpecializationUUID().equals(specializationUUID))) {
			throw new NoSuchElementException(String.format("No such specialization in system %s", specializationUUID ));
		}
		var specialization = getSpecialization(specializationUUID);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	private Specialization getSpecialization(UUID specializationUUID) {
		Optional<Specialization> optionalSpecialization = specializationRepository.findBySpecializationUUID(specializationUUID);
		if (optionalSpecialization.isEmpty()) {
			throw new NoSuchElementException(String.format("No such specialization in system %s", specializationUUID ));
		}
		return optionalSpecialization.get();
	}
}
