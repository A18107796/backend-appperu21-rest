package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.educacionperu21.apirest.entities.Matricula_Pagos;

import java.util.List;
import java.util.Optional;

public interface MatriculaPagoDAO extends GenericJPAStatusRepository<Matricula_Pagos, Integer>{


    @Query("select m from Matricula_Pagos m where m.fecha_venc >= current_date and m.matricula.id = ?1 and m.estado = ?2 order by m.fecha_venc")
    List<Matricula_Pagos> getMatriculaPagoCercana(Integer idMatricula, Estado estado);
}
