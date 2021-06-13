package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.CursoDAO;
import com.educacionperu21.apirest.entities.Curso;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.ICursoService;

@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, CursoDAO, Integer> implements ICursoService{

}
