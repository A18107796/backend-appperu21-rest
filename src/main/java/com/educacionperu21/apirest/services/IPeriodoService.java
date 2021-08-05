package com.educacionperu21.apirest.services;

import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

public interface IPeriodoService extends GenericServiceWithStatus<Periodo, Integer> {


    boolean ExistsPeriodo(Integer id);

    Optional<Periodo> getMostRecentPeriodo();

    List<Periodo> crearAnioAcademico(Date fecha_inicio, int cada, int meses_duración, String nombre_periodo);

    List<Periodo> filtrar_periodo_reciente(String estado);

    List<Periodo> checkPeriodo(String estado);

    int updateStatus(Estado estado, Integer id);

    boolean updateAllStatus(List<Periodo> periodos, Estado estado);

    boolean sincronizarPeriodos();

    Periodo checkCercanoPeriodoToIncripcion();

}
