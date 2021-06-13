package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.TipoEspecializacionDAO;
import com.educacionperu21.apirest.entities.Especializacion_Tipo;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IEspecializacionTipoService;

@Service
public class EspecializacionTipoImpl extends GenericServiceImpl<Especializacion_Tipo, TipoEspecializacionDAO, Integer>
		implements IEspecializacionTipoService {

}
