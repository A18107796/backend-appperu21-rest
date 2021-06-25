package com.educacionperu21.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Provincia;

public interface ProvinciaDAO extends JpaRepository<Provincia, Integer>{
	
	
	@Query("select p from Provincia p where p.departamento.id = ?1")
	List<Provincia> findProvinciaByIDDep(Integer id);
}
