package com.test.haulmont.controller;


import com.test.haulmont.model.Doctor;
import com.test.haulmont.model.DoctorCountDTO;
import com.test.haulmont.model.Patient;
import com.test.haulmont.model.Recipe;
import com.test.haulmont.repository.DoctorRepository;
import com.test.haulmont.repository.PatientRepository;
import com.test.haulmont.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    RecipeRepository recipeRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;

@GetMapping
    public List<Recipe> getAllRecipe(){
        return recipeRepository.findAll();
    }

@PostMapping
    public Recipe saveRecipe(@RequestBody Recipe recipe){
        return recipeRepository.save(recipe);
    }

@GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id){
        return recipeRepository.findById(id).get();
    }

@DeleteMapping("/{id}")
    public void deleteRecipeById(@PathVariable Long id){
        recipeRepository.deleteById(id);
    }

@PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe){
        Recipe oldRecipe = recipeRepository.findById(id).get();
        oldRecipe.setDescription(recipe.getDescription());
        oldRecipe.setPatient(recipe.getPatient());
        oldRecipe.setDoctor(recipe.getDoctor());
        oldRecipe.setManufacture(recipe.getManufacture());
        oldRecipe.setDuration(recipe.getDuration());
        oldRecipe.setPriority(recipe.getPriority());
        return recipeRepository.save(recipe);
    }

@PostMapping("/addrecipe/{doctorid}/{patientid}")
    public Recipe getDoctorAndPatient(@PathVariable Long doctorid, @PathVariable Long patientid, @RequestBody Recipe recipe) {
    Doctor doctor = doctorRepository.findById(doctorid).get();
    Patient patient = patientRepository.findById(patientid).get();
    recipe.setDoctor(doctor);
    recipe.setPatient(patient);
    return recipeRepository.save(recipe);
}

@GetMapping("/bydescription/{description}")
    public List<Recipe> findAllByDescription(@PathVariable String description){
    return recipeRepository.findAllByDescription(description);
}
    @GetMapping("/orderbydescription")
    public List<Recipe> findAllOrderByDescription(){
        return recipeRepository.findAllByOrderByDescription();
    }

    @GetMapping("/countrecipe1")
    public List<DoctorCountDTO> count(){
    return recipeRepository.countRecipe();
    }

}
