package com.educacionperu21.apirest.dao;

import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Matricula;
import org.springframework.transaction.annotation.Transactional;

public interface MatriculasDAO extends GenericJPAStatusRepository<Matricula, Integer> {

	@Query(value = "SELECT * FROM" + " matriculas" + " where " + "matriculas.id_periodo = "
			+ "( select periodos.id from periodos where periodos.fecha_inicio = (select max(periodos.fecha_inicio) from periodos))"
			+ "and matriculas.id_estudiante = ?1", nativeQuery = true)
	Optional<Matricula> findMostRecentMatriculaById(Integer id);

	@Query(value = "CALL estudiante_matriculado(:idStudent);", nativeQuery = true)
	Optional<Matricula> studentMatriculado(Integer idStudent);

	@Query(value = "select m from Matricula m where m.estudiante.id = ?1")
	List<Matricula> getMatriculaByEstudiante(Integer idStudent);

	@Transactional
	@Modifying
	@Query("update Matricula m set m.estado = ?1 where m.id = ?2")
	int updateEstado(Estado estado, Integer id);

}
