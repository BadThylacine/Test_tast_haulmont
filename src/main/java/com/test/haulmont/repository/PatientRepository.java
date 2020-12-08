package com.test.haulmont.repository;

import com.test.haulmont.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p from Patient p where p.name like concat('%', :name, '%')")
    List<Patient> findByName(@Param("name") String name);
}
