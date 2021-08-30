package com.educacionperu21.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educacionperu21.apirest.entities.Especializacion;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EspecializacionDAO extends GenericJPAStatusRepository<Especializacion, Integer> {


    @Query(value = "select e.*, COUNT(*) as 'nveces' from matriculas m inner join especializaciones e on e.id = m.id_especializacion where m.id_periodo = ?1 GROUP by m.id_especializacion", nativeQuery = true)
    List<Map<Especializacion, Integer>> findMonstEspecializationByPeriodo(Integer idPeriodo);

    @Query(value = "select e.*, COUNT(*) as 'nveces' from matriculas m inner join especializaciones e on e.id = m.id_especializacion  GROUP by m.id_especializacion", nativeQuery = true)
    List<Map<Especializacion, Integer>> findMonstEspecialization();
}
