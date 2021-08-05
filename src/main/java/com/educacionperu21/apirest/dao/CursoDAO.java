package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Curso;

public interface CursoDAO extends GenericJPAStatusRepository<Curso, Integer> {

}
