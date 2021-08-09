package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.entities.Periodo;
import com.educacionperu21.apirest.enums.Estado;
import com.educacionperu21.apirest.exceptions.BadRequestException;
import com.educacionperu21.apirest.exceptions.NotFoundException;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.MatriculaPagoDAO;
import com.educacionperu21.apirest.entities.Matricula_Pagos;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IMatriculaPagoService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MatriculaPagoServiceImpl extends GenericServiceWithStatusImpl<Matricula_Pagos, MatriculaPagoDAO, Integer>
		implements IMatriculaPagoService {

	@Override
	public Optional<Matricula_Pagos> getMatriculaPagoCercana(Integer idMatricula, Estado estado) {
		if(estado == null){
			throw new BadRequestException("Verifique datos");
		}

		return Optional.of(dao.getMatriculaPagoCercana(idMatricula, estado).get(0));
	}
}
