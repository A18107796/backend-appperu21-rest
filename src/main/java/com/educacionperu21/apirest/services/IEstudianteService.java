package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

import java.util.List;

public interface IEstudianteService extends GenericServiceWithStatus<Estudiante, Integer> {

    boolean emailsExists(String email);

    boolean dniExists(String dni);
}
