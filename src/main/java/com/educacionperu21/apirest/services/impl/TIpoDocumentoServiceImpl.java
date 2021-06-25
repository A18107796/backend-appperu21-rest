package com.educacionperu21.apirest.services.impl;

import org.springframework.stereotype.Service;

import com.educacionperu21.apirest.dao.TipoDocumentoDAO;
import com.educacionperu21.apirest.dao.TipoEspecializacionDAO;
import com.educacionperu21.apirest.entities.Tipo_Documento;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.ITipoDocumentoService;

@Service
public class TIpoDocumentoServiceImpl extends GenericServiceImpl<Tipo_Documento, TipoDocumentoDAO, Integer> implements ITipoDocumentoService {
	
	
}
