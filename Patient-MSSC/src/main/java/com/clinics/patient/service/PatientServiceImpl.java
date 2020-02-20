package com.clinics.patient.service;


import com.clinics.common.DTO.request.RegisterPatientDTO;
import com.clinics.common.DTO.response.PatientRegisterResponseDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PatientClient patientClient;

    @Override
    public PatientRegisterResponseDTO addPatient(RegisterPatientDTO registerPatientDTO) {
        //TODO implement
        //TODO map from DTO to patient
        //TODO call patientClient first
        //TODO

        Patient patient = modelMapper.map(registerPatientDTO, Patient.class);
        patientClient.activatePatientInAuth(patient);
        //TODO patient save to DB
        //TODO map saved patient to PatientRegisterResponseDTO and return




//        var userAuth = modelMapper.map(registerUserDTO, UserDAO.class);
//        userAuth.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
//        userRepository.save(userAuth);
//        var userResponse = modelMapper.map(userAuth, UserResponseDTO.class);
//        String token = makeJwtToken(userAuth);
//        userResponse.setToken(TOKEN_PREFIX + token);
//        return userResponse;
        return null;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findByUuid(UUID UUID) {
        return patientRepository.findByUuid(UUID);
    }

    @Override
    public Optional<Patient> findById(Long ID) {
        return patientRepository.findById(ID);
    }

    @Override
    public void deleteById(Long ID) {
        patientRepository.deleteById(ID);
    }

    @Override
    public Patient editPatient(Patient patient) {

        patientValid(patient);
        Optional<Patient> existingPatient = patientRepository.findById(patient.getId());

        if(existingPatient.isPresent())
        {
            //TODO change patient data
            existingPatient.get().setPesel(patient.getPesel());
            return patientRepository.save(existingPatient.get());
        }else{
            return null;
        }
    }

    private void patientValid(Patient patient){
        // TODO patient data validation
        // Throw exception if f.ex pesel is wrong
    }
}
