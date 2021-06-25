package com.educacionperu21.apirest.services;

import java.util.List;

import com.educacionperu21.apirest.entities.Departamento;
import com.educacionperu21.apirest.entities.Distrito;
import com.educacionperu21.apirest.entities.Provincia;

public interface IUbicacionService{
	
	List<Departamento> getDepartamentos();
	List<Provincia> getProvincias();
	List<Distrito> getDistritos();
	
	List<Provincia> getProvinciasByIDDep(Integer id);
	List<Distrito> getDistritoByIDProv(Integer id);
	
}
