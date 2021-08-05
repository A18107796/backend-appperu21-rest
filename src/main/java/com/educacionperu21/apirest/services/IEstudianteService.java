package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService extends GenericServiceWithStatus<Estudiante, Integer> {

    boolean emailsExists(String email);

    boolean dniExists(String dni);

    Optional<Estudiante> findStudentByDNI(String dni);

    int updateEstado(Estado estado, Integer id);
}
