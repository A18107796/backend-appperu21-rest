package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.MatriculaAnuladaDAO;
import com.educacionperu21.apirest.entities.Matricula;
import com.educacionperu21.apirest.entities.MatriculaAnulada;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEstudianteService;
import com.educacionperu21.apirest.services.IMatriculaAnuladaService;
import com.educacionperu21.apirest.services.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculaAnuladaServiceImpl extends GenericServiceImpl<MatriculaAnulada, MatriculaAnuladaDAO, Integer> implements IMatriculaAnuladaService {

    @Autowired
    private IMatriculaService matriculaService;

    @Autowired
    private IEstudianteService estudianteService;

    @Override
    public MatriculaAnulada save(MatriculaAnulada alumno) {
        if(!estudianteService.ExistsEntity(alumno.getEstudiante().getId())){
            throw new NotFoundException("El alumno no existe, verifique datos");
        }

        if(!matriculaService.ExistsEntity(alumno.getMatricula().getId())){
            throw new NotFoundException("La matricula no existe, verifique datos");
        }

        if(alumno.getMatricula().getEstado() != Estado.ACTIVO || alumno.getMatricula().getEstado() != Estado.PENDIENTE){
            throw new NotFoundException("Esta matricula no se puede anular, verifique datos");
        }




        return super.save(alumno);
    }
}
