package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Curso;

public interface CursoDAO extends JpaRepository<Curso, Integer> {

}
