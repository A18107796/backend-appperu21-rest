package com.educacionperu21.apirest.dao;

import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.enums.Estado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.educacionperu21.apirest.entities.Matricula_Pagos;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface MatriculaPagoDAO extends GenericJPAStatusRepository<Matricula_Pagos, Integer> {


    @Query("select m from Matricula_Pagos m where m.fecha_venc >= current_date and m.matricula.id = ?1 and m.estado = ?2 order by m.fecha_venc")
    List<Matricula_Pagos> getMatriculaPagoCercana(Integer idMatricula, Estado estado);

    @Transactional
    @Modifying
    @Query("update Matricula_Pagos m set m.estado = ?1 where m.id = ?2")
    int updateEstado(Estado estado, Integer id);

    @Transactional
    @Modifying
    @Query("update Matricula_Pagos m set m.fecha_pago = null where m.id = ?1")
    void updateFechaPago(Integer id);

    @Query(value = "select m from Matricula_Pagos m where m.matricula.estudiante.id = ?1 and (m.estado = 'PENDIENTE' or m.estado = 'VENCIDO' )")
    List<Matricula_Pagos> hasDeudas(Integer id);

    @Query(value = "select matricula_pagos.estado, count(*) as 'nveces' from matricula_pagos GROUP by matricula_pagos.estado", nativeQuery = true)
    List<Map<String, Integer>> findStadisticsOfStatus();
}
