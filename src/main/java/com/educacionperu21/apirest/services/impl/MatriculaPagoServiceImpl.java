package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.MatriculaPagoDAO;
import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IMatriculaPagoService;

@Service
public class MatriculaPagoServiceImpl extends GenericServiceImpl<Matricula_Pagos, MatriculaPagoDAO, Integer>
		implements IMatriculaPagoService {

}
