package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Empleado;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

public interface IEmpleadoService extends GenericServiceWithStatus<Empleado, Integer> {

	boolean emailExists(String email);

	boolean dniExists(String email);

	Empleado update(Empleado empleado);
}
