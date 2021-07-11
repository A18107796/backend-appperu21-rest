package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.educacionperu21.apirest.entities.Matricula_Pagos;

public interface MatriculaPagoDAO extends JpaRepository<Matricula_Pagos, Integer>{

}
