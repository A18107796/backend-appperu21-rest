package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

import java.util.Map;
import java.util.Optional;

public interface IMatriculaPagoService extends GenericServiceWithStatus<Matricula_Pagos, Integer> {

    Optional<Matricula_Pagos> getMatriculaPagoCercana(Integer idMatricula, Estado estado);

    Map<String, Object> verifyCuotas();

    boolean verifyCuotaByIDStudents(Integer id);

    int hasDeudas(Integer idEstudiante);

    void updateFecha(Integer id);

}
