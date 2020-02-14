package com.clinics.patient.service;


import com.clinics.patient.entity.Patient;
import com.clinics.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
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
