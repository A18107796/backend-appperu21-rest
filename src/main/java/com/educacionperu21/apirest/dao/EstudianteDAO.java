package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Estudiante;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EstudianteDAO extends GenericJPAStatusRepository<Estudiante, Integer> {


    @Query(nativeQuery = true, value = "select e.email from estudiantes e where e.email = ?1")
    List<String> emailsExists(String email);

    @Query(nativeQuery = true, value = "select e.num_doc from estudiantes e where e.num_doc = ?1")
    List<String> dniExists(String dni);

    @Query("select e from Estudiante e where e.num_doc = ?1")
    Optional<Estudiante> findStudentByDNI(String dni);

    @Transactional
    @Modifying
    @Query("update Estudiante e set e.estado = ?1 where e.id = ?2")
    int updateEstado(Estado estado, Integer id);

    @Query("select count(e) from Estudiante e")
    int getCountStudents();


}
