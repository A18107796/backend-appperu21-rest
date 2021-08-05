package com.educacionperu21.apirest.services.impl;

import java.util.Optional;

import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educacionperu21.apirest.dao.EmpleadoDAO;
import com.educacionperu21.apirest.dao.EspecializacionDAO;
import com.educacionperu21.apirest.entities.Especializacion;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.InternalServerError;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEspecializacionService;

@Service
public class EspecializacionServiceImpl extends GenericServiceWithStatusImpl<Especializacion, EspecializacionDAO, Integer>
		implements IEspecializacionService {

	@Override
	@Transactional
	public boolean changeState(Especializacion e) {
		Optional<Especializacion> esp = this.findById(e.getId());
		if (esp.isEmpty()) {
			throw new NotFoundException("La especializacion no existe");
		}
		esp.get().setEstado(e.getEstado());
		try {
			this.save(esp.get());
		} catch (Exception exception) {
			throw new InternalServerError(exception.getMessage());
		}
		return true;

	}

	@Override
	@Transactional
	public Especializacion update(Especializacion especializacion) {
		return dao.save(especializacion);
	}

	@Override
	public boolean ExistsEspecializacion(Integer id) {
		return this.dao.existsById(id);
	}

}
