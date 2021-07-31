package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Estudiante;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteDAO extends GenericJPAStatusRepository<Estudiante, Integer>{


    @Query(nativeQuery = true, value = "select e.email from estudiantes e where e.email = ?1")
    List<String> emailsExists(String email);

    @Query(nativeQuery = true, value = "select e.num_doc from estudiantes e where e.num_doc = ?1")
    List<String> dniExists(String dni);

    @Query("select e from Estudiante e where e.num_doc = ?1")
    Optional<Estudiante> findStudentByDNI(String dni);
}
