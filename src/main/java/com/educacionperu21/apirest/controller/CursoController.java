package com.educacionperu21.apirest.controller;

import org.springframework.stereotype.Controller;

import com.educacionperu21.apirest.entities.Curso;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.services.ICursoService;
import com.educacionperu21.apirest.services.IEmpleadoService;

@Controller
public class CursoController extends GenericController<Curso, Integer, ICursoService>{

}
