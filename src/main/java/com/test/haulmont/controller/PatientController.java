package com.test.haulmont.controller;

import com.test.haulmont.model.Patient;
import com.test.haulmont.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    PatientRepository patientRepository;

@GetMapping
    public List<Patient> getAllPatient(){
    return patientRepository.findAll();
    }

@PostMapping
    public Patient savePatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

@GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        return patientRepository.findById(id).get();
    }

@DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id){
        patientRepository.deleteById(id);
    }

@PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        Patient oldPatient = patientRepository.findById(id).get();
        oldPatient.setName(patient.getName());
        oldPatient.setSurname(patient.getSurname());
        oldPatient.setPatronymic(patient.getPatronymic());
        oldPatient.setPhoneNumber(patient.getPhoneNumber());
        return patientRepository.save(oldPatient);
    }

}
