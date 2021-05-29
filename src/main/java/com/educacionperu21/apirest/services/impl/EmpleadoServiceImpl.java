package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.EmpleadoDAO;
import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEmpleadoService;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, EmpleadoDAO, Integer>
		implements IEmpleadoService {

}
