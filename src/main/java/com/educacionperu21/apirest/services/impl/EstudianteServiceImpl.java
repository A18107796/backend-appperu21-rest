package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EstudianteDAO;
import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEstudianteService;

import java.util.List;

@Service
public class EstudianteServiceImpl extends GenericServiceWithStatusImpl<Estudiante, EstudianteDAO, Integer>
        implements IEstudianteService {

    @Override
    public boolean emailsExists(String email) {
        List<String> emails = dao.emailsExists(email);
        if (emails.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean dniExists(String dni) {
        List<String> emails = dao.dniExists(dni);
        if (emails.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
