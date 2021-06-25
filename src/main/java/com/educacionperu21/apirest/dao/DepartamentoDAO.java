package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Departamento;

public interface DepartamentoDAO extends JpaRepository<Departamento, Integer> {
	
	
}
