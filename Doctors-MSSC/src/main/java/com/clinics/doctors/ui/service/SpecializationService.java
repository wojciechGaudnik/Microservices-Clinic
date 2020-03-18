package com.clinics.doctors.ui.service;

import com.clinics.common.DTO.request.outer.doctor.SpecializationDTO;
import com.clinics.common.DTO.response.outer.SpecializationResponseDTO;
import com.clinics.doctors.data.model.Specialization;
import com.clinics.doctors.data.repositorie.DoctorRepository;
import com.clinics.doctors.data.repositorie.SpecializationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class SpecializationService {

	final private SpecializationRepository specializationRepository;
	final private DoctorRepository doctorRepository;
	final private DoctorService doctorService;
	final private ModelMapper modelMapper;

	public SpecializationService(
			SpecializationRepository specializationRepository,
			DoctorRepository doctorRepository,
			DoctorService doctorService,
			ModelMapper modelMapper) {
		this.specializationRepository = specializationRepository;
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
		this.modelMapper = modelMapper;

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

	public SpecializationResponseDTO save(SpecializationDTO specializationDTO) {
		Specialization specialization = modelMapper.map(specializationDTO, Specialization.class);
		specialization.setSpecializationUUID(UUID.randomUUID());
		return modelMapper.map(specializationRepository.save(specialization), SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO save(SpecializationDTO specializationDTO, UUID doctorUUID) {
		var doctor = doctorService.getByUUID(doctorUUID);
		if (specializationRepository.existsByName(specializationDTO.getName())) {
			if (doctor.getSpecializations().stream().anyMatch(s -> s.getName().equals(specializationDTO.getName()))) {
				throw new DataIntegrityViolationException("Doctor already have such specialization");
			}
			var specialization = specializationRepository.findByName(specializationDTO.getName());
			doctor.getSpecializations().add(specialization);
			doctorRepository.save(doctor);
			return modelMapper.map(specialization, SpecializationResponseDTO.class);
		}
		Specialization specialization = modelMapper.map(specializationDTO, Specialization.class);
		specialization.setSpecializationUUID(UUID.randomUUID());
		doctor.getSpecializations().add(specialization);
		specializationRepository.save(specialization);
		doctorRepository.save(doctor);
		return modelMapper.map(specialization, SpecializationResponseDTO.class);
	}

	public SpecializationResponseDTO save(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorService.getByUUID(doctorUUID);
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


//	public CalendarResponseDTO save(AddEditCalendarDTO addEditCalendarDTO, UUID doctorUUID) {
//		var doctor = doctorService.getDoctor(doctorUUID);
//		Calendar calendar = modelMapper.map(addEditCalendarDTO, Calendar.class);
//		calendar.setCalendarUUID(UUID.randomUUID());
//		calendar.setDoctor(doctor);
//		calendarRepository.save(calendar);
//		return modelMapper.map(calendar, CalendarResponseDTO.class);
//	}


	public void edit(SpecializationDTO specializationDTO, UUID specializationUUID) {
		var specialization = getSpecialization(specializationUUID);
		modelMapper.map(specializationDTO, specialization);
		specializationRepository.save(specialization);
	}

	public void delete(UUID specializationUUID, UUID doctorUUID) {
		var doctor = doctorService.getByUUID(doctorUUID);
		var specialization = getSpecialization(specializationUUID);
		doctor.getSpecializations().remove(specialization);
		doctorRepository.save(doctor);
	}

	public void delete(UUID specializationUUID) {
		var specialization = getSpecialization(specializationUUID);
		specializationRepository.delete(specialization);
	}

	public List<SpecializationResponseDTO> getDoctorSpecializations(UUID doctorUUID) {
		var doctor = doctorService.getByUUID(doctorUUID);
		return specializationRepository.findAllByDoctors(doctor)
				.stream()
				.map(specialization -> modelMapper.map(specialization, SpecializationResponseDTO.class))
				.collect(Collectors.toList());
	}

	public SpecializationResponseDTO getDoctorSpecialization(UUID doctorUUID, UUID specializationUUID) {
		var doctor = doctorService.getByUUID(doctorUUID);
		if (doctor.getSpecializations().stream().noneMatch(specialization -> specialization.getSpecializationUUID().equals(specializationUUID))) {
			throw new NoSuchElementException(String.format("Doctor doesn't have such specialization %s", specializationUUID ));
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
