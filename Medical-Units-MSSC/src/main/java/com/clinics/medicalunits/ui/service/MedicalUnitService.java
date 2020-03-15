package com.clinics.medicalunits.ui.service;

import com.clinics.common.DTO.request.outer.medicalUnit.RegisterMedicalUnitDTO;
import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.medicalunits.ui.model.MedicalUnit;
import com.clinics.medicalunits.ui.repositorie.MedicalUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Slf4j
@Service
public class MedicalUnitService {

	final private MedicalUnitRepository medicalUnitRepository;
	final private ModelMapper modelMapper;
	final private RestTemplate restTemplate;
	final private Environment environment;

	public MedicalUnitService(
			MedicalUnitRepository medicalUnitRepository,
			ModelMapper modelMapper,
			RestTemplate restTemplate,
			Environment environment) {
		this.medicalUnitRepository = medicalUnitRepository;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.modelMapper = modelMapper;
	}

	public List<MedicalUnitResponseDTO> getAll() {
		List<MedicalUnitResponseDTO> medicalUnitResponseDTOList = new LinkedList<>();
		for (var one :medicalUnitRepository.findAll()) {
			medicalUnitResponseDTOList.add(modelMapper.map(one, MedicalUnitResponseDTO.class));
		}

		return medicalUnitResponseDTOList;
	}

	public MedicalUnitResponseDTO save(RegisterMedicalUnitDTO registerMedicalUnitDTO) {
		var medicalUnit = modelMapper.map(registerMedicalUnitDTO, MedicalUnit.class);
		medicalUnit.setMedicalUnitUUID(UUID.randomUUID());
		return modelMapper.map(medicalUnitRepository.save(medicalUnit), MedicalUnitResponseDTO.class);
	}

	public MedicalUnitResponseDTO getByUUID(UUID medicalUnitUUID) {
		var medicalUnit = medicalUnitRepository.findByMedicalUnitUUID(medicalUnitUUID);
		if (medicalUnit.isEmpty()) {
			throw new NoSuchElementException("No such medial unit in system");
		}
		return modelMapper.map(medicalUnit.get(), MedicalUnitResponseDTO.class);
	}
}


