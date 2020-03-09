package com.clinics.medicalunits.ui.service;

import com.clinics.common.DTO.request.outer.EditDoctorDTO;
import com.clinics.common.DTO.request.inner.EditUserDTO;
import com.clinics.common.DTO.request.outer.RegisterDoctorDTO;
import com.clinics.common.DTO.request.outer.RegisterMedicalUnitDTO;
import com.clinics.common.DTO.response.outer.DoctorResponseDTO;
import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.medicalunits.ui.model.MedicalUnit;
import com.clinics.medicalunits.ui.repositorie.MedicalUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
		this.environment = environment;
	}

	public List<MedicalUnitResponseDTO> getAll() {
		List<MedicalUnitResponseDTO> medicalUnitResponseDTOList = new LinkedList<>();
		for (var one :medicalUnitRepository.getAll()) {
			medicalUnitResponseDTOList.add(modelMapper.map(one, MedicalUnitResponseDTO.class));
		}
		return medicalUnitResponseDTOList;
	}

	public void save(RegisterMedicalUnitDTO registerMedicalUnitDTO) {
		var medicalUnit = modelMapper.map(registerMedicalUnitDTO, MedicalUnit.class);
		medicalUnit.setMedicalUnitUUID(UUID.randomUUID());
		medicalUnitRepository.save(medicalUnit);
	}
}


