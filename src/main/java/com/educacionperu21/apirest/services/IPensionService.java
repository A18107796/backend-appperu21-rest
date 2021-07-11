package com.educacionperu21.apirest.services;

import java.util.List;

import com.educacionperu21.apirest.entities.Pension;
import com.educacionperu21.apirest.generics.service.GenericService;

public interface IPensionService extends GenericService<Pension, Integer>{
	
	List<Pension> registerPensiones(int numCuotas, double montoTotal);
}
