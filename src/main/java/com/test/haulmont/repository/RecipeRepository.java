package com.test.haulmont.repository;

import com.test.haulmont.model.Doctor;
import com.test.haulmont.model.DoctorCountDTO;
import com.test.haulmont.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public List<Recipe> findAllByDescription(String description);
    public List<Recipe> findAllByDuration(String duration);
    public List<Recipe> findAllByManufacture(String manufacture);

    public List<Recipe> findAllByOrderByDescription();
    public List<Recipe> findAllByOrderByDuration();
    public List<Recipe> findAllByOrderByManufacture();

    @Query(value = "SELECT new com.test.haulmont.model.DoctorCountDTO(count (r.doctor)) FROM Recipe r  GROUP BY r.doctor  ")
    List<DoctorCountDTO> countRecipe();

    @Query(value = "select w.duration from Recipe w ")
    List<String> recipesList();



    @Query("SELECT e from Recipe e where e.description like concat('%', :name, '%')")
    List<Recipe> findByName(@Param("name") String name);
}
