package com.educacionperu21.apirest.services;

import java.util.Optional;

import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.generics.service.GenericService;

public interface IPeriodoService extends GenericService<Periodo, Integer>{
	
	
	boolean ExistsPeriodo(Integer id);
	
	Optional<Periodo> getMostRecentPeriodo();
}
