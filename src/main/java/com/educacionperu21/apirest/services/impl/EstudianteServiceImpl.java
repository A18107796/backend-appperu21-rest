package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EstudianteDAO;
import com.educacionperu21.apirest.entities.Estudiante;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEstudianteService;

@Service
public class EstudianteServiceImpl extends GenericServiceImpl<Estudiante, EstudianteDAO, Integer>
		implements IEstudianteService {

}
