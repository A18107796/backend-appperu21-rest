package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.dao.GenericJPAStatusRepository;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;

import java.util.List;
import java.util.Optional;

public interface IPagoService extends GenericServiceWithStatus<Pago, Integer> {

    Integer findLastId();

    int anularPago(Integer idPago);

    double getGanancias();

    double getGananciasBetweenFechas(String estado, String fecha_inicio, String fecha_fin);

    List<Pago> getPagosBetweenFechas(String estado, String fecha_inicio, String fecha_fin);
}
