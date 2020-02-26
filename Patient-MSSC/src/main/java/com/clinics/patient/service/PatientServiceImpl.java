package com.clinics.patient.service;


import com.clinics.common.DTO.request.RegisterPatientDTO;
import com.clinics.common.DTO.response.DoctorResponseDTO;
import com.clinics.common.DTO.response.PatientRegisterResponseDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.clinics.patient.client.PatientClient;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    RestTemplate restTemplate;

    @Override
    public PatientRegisterResponseDTO addPatient(RegisterPatientDTO registerPatientDTO, HttpServletRequest request) {
//        Patient patient = modelMapper.map(registerPatientDTO, Patient.class);
//        patientClient.activatePatientInAuth(patient);
        //TODO patient save to DB
        //TODO map saved patient to PatientRegisterResponseDTO and return

        System.out.println(registerPatientDTO);
        String url = String.format("http://auth/auth/users/%s", registerPatientDTO.getUuid());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtProperties.TOKEN_REQUEST_HEADER, request.getHeader(JwtProperties.TOKEN_REQUEST_HEADER));
        HttpEntity<String> testName = new HttpEntity<>("Empty Request", httpHeaders);
        try {
            System.out.println(url);
            System.out.println(testName);
            ResponseEntity<Void> responseFromAuth = restTemplate.exchange(url, HttpMethod.PUT, testName, Void.class);
        } catch (Exception e) {
            throw new NoSuchElementException("There is no such patient in AUTH");
        }
        var patient = modelMapper.map(registerPatientDTO, Patient.class);
        patientRepository.save(patient);
        return modelMapper.map(patient, PatientRegisterResponseDTO.class);
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
