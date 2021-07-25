package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Estudiante;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteDAO extends GenericJPAStatusRepository<Estudiante, Integer>{


    @Query(nativeQuery = true, value = "select e.email from estudiante e where e.email = ?1")
    List<String> emailsExists(String email);

    @Query(nativeQuery = true, value = "select e.num_doc from estudiante e where e.num_doc = ?1")
    List<String> dniExists(String dni);
}
