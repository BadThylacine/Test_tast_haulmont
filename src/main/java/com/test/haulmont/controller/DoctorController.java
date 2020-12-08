package com.test.haulmont.controller;

import com.test.haulmont.model.Doctor;
import com.test.haulmont.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    DoctorRepository doctorRepository;

@GetMapping
    public List<Doctor> getAllDoctor(){
    return doctorRepository.findAll();
}

    @GetMapping("/countall")
    public Map<String, Integer> getCountDoctor(){
        Stream<Doctor> doctorStream = doctorRepository.findAll().stream();
        Map<String, Integer> map = doctorStream.collect(Collectors.toMap(d -> d.getName(), d -> d.getRecipes().size()));
        return doctorRepository.findAll().stream().collect(Collectors.toMap(d -> ((Doctor)d).getName(), d-> ((Doctor)d).getRecipes().size()));
    }

@PostMapping
    public Doctor saveDoctor(@RequestBody Doctor doctor){
    return doctorRepository.save(doctor);
}

@GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
    return doctorRepository.findById(id).get();
}

@DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id){
    doctorRepository.deleteById(id);
}

@PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        Doctor oldDoctor = doctorRepository.findById(id).get();
        oldDoctor.setName(doctor.getName());
        oldDoctor.setSurname(doctor.getSurname());
        oldDoctor.setPatronymic(doctor.getPatronymic());
        oldDoctor.setSpecialisation(doctor.getSpecialisation());
        return doctorRepository.save(oldDoctor);
    }


}
