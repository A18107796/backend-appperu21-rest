package com.educacionperu21.apirest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.DepartamentoDAO;
import com.educacionperu21.apirest.dao.DistritoDAO;
import com.educacionperu21.apirest.dao.ProvinciaDAO;
import com.educacionperu21.apirest.entities.Departamento;
import com.educacionperu21.apirest.entities.Distrito;
import com.educacionperu21.apirest.entities.Provincia;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.services.IUbicacionService;

@Service
public class UbicacionServiceImpl implements IUbicacionService{

	@Autowired
	private DepartamentoDAO daoDepartamento;
	
	@Autowired
	private ProvinciaDAO daoProvincia;
	
	@Autowired
	private DistritoDAO daoDistrito;
	
	
	@Override
	public List<Departamento> getDepartamentos() {
		return daoDepartamento.findAll();
	}

	@Override
	public List<Provincia> getProvincias() {
		return daoProvincia.findAll();
	}

	@Override
	public List<Distrito> getDistritos() {
		return daoDistrito.findAll();
	}

	@Override
	public List<Provincia> getProvinciasByIDDep(Integer id) {
		List<Provincia> provincias = daoProvincia.findProvinciaByIDDep(id);
		if(provincias == null || provincias.size() == 0) {
			throw new NotFoundException("No existen provincias en el ID indicado");
		}
		return provincias;
	}

	@Override
	public List<Distrito> getDistritoByIDProv(Integer id) {
		List<Distrito> distritos = daoDistrito.findDistritoBYIDProv(id);
		if(distritos == null || distritos.size() == 0) {
			throw new NotFoundException("No existen distritos en el ID indicado");
		}
		return distritos;
	}
	
}
