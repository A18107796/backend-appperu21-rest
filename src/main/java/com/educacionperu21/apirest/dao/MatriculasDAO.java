package com.educacionperu21.apirest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Matricula;

public interface MatriculasDAO extends JpaRepository<Matricula, Integer> {

	@Query(value = "SELECT * FROM" + " matriculas" + " where " + "matriculas.id_periodo = "
			+ "( select periodos.id from periodos where periodos.fecha_inicio = (select max(periodos.fecha_inicio) from periodos))"
			+ "and matriculas.id_estudiante = ?1", nativeQuery = true)
	Optional<Matricula> findMostRecentMatriculaById(Integer id);
}
