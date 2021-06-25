package com.educacionperu21.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Distrito;
import com.educacionperu21.apirest.entities.Provincia;

public interface DistritoDAO extends JpaRepository<Distrito, Integer>{

	
	@Query("select d from Distrito d where d.provincia.id = ?1")
	List<Distrito> findDistritoBYIDProv(Integer id);
}
