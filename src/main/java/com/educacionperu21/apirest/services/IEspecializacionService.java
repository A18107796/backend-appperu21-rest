package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;

public interface IEspecializacionService extends GenericServiceWithStatus<Especializacion, Integer> {

	boolean changeState(Especializacion especializacion);
	
	Especializacion update(Especializacion especializacion); 
	
	boolean ExistsEspecializacion(Integer id);
}
