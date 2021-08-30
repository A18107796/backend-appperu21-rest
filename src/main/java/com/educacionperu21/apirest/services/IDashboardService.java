package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Especializacion;

import java.util.List;
import java.util.Map;

public interface IDashboardService {

    List<Map<String, Integer>> findStadisticPagoStatus();

    List<Map<Especializacion, Integer>> findMonstEspecialization(Integer idPeriodo);

    List<Map<Especializacion, Integer>> findMonstEspecialization();

    List<Map<String, Integer>> findStadisticsOfStatus();

    Map<String, Integer> findStudentCount();

    Map<String, Integer> findEmpleadosCount();

    Map<String, Integer> findMatriculasCount();
}

