package com.test.haulmont.repository;

import com.test.haulmont.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d from Doctor d where d.name like concat('%', :name, '%')")
    List<Doctor> findByName(@Param("name") String name);
}
