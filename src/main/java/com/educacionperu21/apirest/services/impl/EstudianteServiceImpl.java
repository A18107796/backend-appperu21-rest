package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EstudianteDAO;
import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEstudianteService;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl extends GenericServiceWithStatusImpl<Estudiante, EstudianteDAO, Integer>
        implements IEstudianteService {

    @Override
    public boolean emailsExists(String email) {
        List<String> emails = dao.emailsExists(email);
        return !emails.isEmpty();
    }

    @Override
    public boolean dniExists(String dni) {
        List<String> emails = dao.dniExists(dni);
        return !emails.isEmpty();
    }

    @Override
    public Optional<Estudiante> findStudentByDNI(String dni) {
        Optional<Estudiante> estudianteDB = dao.findStudentByDNI(dni);
        if (estudianteDB.isEmpty()) {
            throw new NotFoundException("El estudiante no existe, Verifique DNI");
        }
        return estudianteDB;
    }

    @Override
    public int updateEstado(Estado estado, Integer id) {
        return dao.updateEstado(estado, id);
    }

    @Override
    public List<Estudiante> getEstudentsMatriculadosByPeriodo(Integer idPeriodo) {
        return dao.getEstudentsMatriculadosByPeriodo(idPeriodo);
    }



    @Override
    public List<Estudiante> getEstudentsMatriculadosByPeriodoAndIdEspecializacion(Integer idPeriodo, Integer idEspecializacion) {
        return dao.getEstudentsMatriculadosByPeriodoAndIdEspecializacion(idPeriodo, idEspecializacion);
    }

    @Override
    public int getCountStudentsMatriculados(Integer idPeriodo) {
        return dao.getCountStudentsMatriculados(idPeriodo);
    }

    @Override
    public List<Estudiante> getEstudentsMatriculados() {
        return dao.getEstudentsMatriculados();
    }


}
