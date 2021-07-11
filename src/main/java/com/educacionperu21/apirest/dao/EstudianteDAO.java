package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Estudiante;

public interface EstudianteDAO extends JpaRepository<Estudiante, Integer>{

}
