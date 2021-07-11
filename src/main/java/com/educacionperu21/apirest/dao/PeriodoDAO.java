package com.educacionperu21.apirest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Periodo;

public interface PeriodoDAO extends JpaRepository<Periodo, Integer>{
	
	
	@Query("SELECT p FROM Periodo p where p.fecha_inicio = (select max(p.fecha_inicio) FROM Periodo p)")
	Optional<Periodo> getMostRecentPeriodo();
}
