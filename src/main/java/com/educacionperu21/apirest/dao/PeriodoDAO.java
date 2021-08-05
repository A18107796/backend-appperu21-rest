package com.educacionperu21.apirest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Periodo;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PeriodoDAO extends GenericJPAStatusRepository<Periodo, Integer>{

	@Query("SELECT p FROM Periodo p where p.fecha_inicio = (select max(p.fecha_inicio) FROM Periodo p)")
	Optional<Periodo> getMostRecentPeriodo();

	@Query(value = "CALL filtrar_periodo_reciente(:status);", nativeQuery = true)
	List<Periodo> filtrar_periodo_reciente(@Param("status") String estado);


	@Query(value = "CALL check_periodo(:status);", nativeQuery = true)
	List<Periodo> check_periodo(@Param("status") String estado);


	@Transactional
	@Modifying
	@Query("update Periodo p set p.estado = ?1 where p.id = ?2")
	int updateEstado(Estado estado, Integer id);
}
