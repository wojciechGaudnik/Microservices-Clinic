package com.clinics.doctors.ui.client;

import com.clinics.common.DTO.response.outer.MedicalUnitResponseDTO;
import com.clinics.doctors.ui.service.JPAimpl.DoctorMedicalUnitClientImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/doctors/{doctorUUID}/medical-units")
public class DoctorMedicalUnitController {


	final private DoctorMedicalUnitClientImpl doctorMedicalUnitClientImpl;

	public DoctorMedicalUnitController(
			DoctorMedicalUnitClientImpl doctorMedicalUnitClientImpl) {
		this.doctorMedicalUnitClientImpl = doctorMedicalUnitClientImpl;
	}

	@GetMapping
	public ResponseEntity<List<MedicalUnitResponseDTO>> getAllDoctorMedicalUnites(@PathVariable UUID doctorUUID){
		return ResponseEntity.ok().body(doctorMedicalUnitClientImpl.getAll(doctorUUID));
	}

	@GetMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> getDoctorsMedicalUnit(
			@PathVariable UUID doctorUUID,
			@PathVariable UUID medicalUnitUUID
	){
		return ResponseEntity.ok().body(doctorMedicalUnitClientImpl.get(doctorUUID, medicalUnitUUID));
	}

	@PostMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<MedicalUnitResponseDTO> addMedicalUniteIntoDoctor(
			@PathVariable UUID medicalUnitUUID,
			@PathVariable UUID doctorUUID) {
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorMedicalUnitClientImpl.save(medicalUnitUUID, doctorUUID));
	}

	@DeleteMapping(value = "/{medicalUnitUUID}")
	public ResponseEntity<Void> removeMedicalUniteFromDoctor(
			@PathVariable UUID medicalUnitUUID,
			@PathVariable UUID doctorUUID) {
		doctorMedicalUnitClientImpl.delete(doctorUUID, medicalUnitUUID);
		return ResponseEntity.ok().build();
	}
}
