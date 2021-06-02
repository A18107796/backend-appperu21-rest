package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EmpleadoDAO;
import com.educacionperu21.apirest.dao.EspecializacionDAO;
import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEspecializacionService;

@Service
public class EspecializacionServiceImpl extends GenericServiceImpl<Especializacion, EspecializacionDAO, Integer>
		implements IEspecializacionService {

}
