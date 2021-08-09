package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.TipoComprobanteDAO;
import com.educacionperu21.apirest.entities.Tipo_Comprobante;
import com.educacionperu21.apirest.generics.controller.GenericController;
import com.educacionperu21.apirest.generics.service.GenericService;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.ITIpoComprobanteService;
import org.springframework.stereotype.Service;

@Service
public class TipoComprobanteServiceImpl extends GenericServiceImpl<Tipo_Comprobante, TipoComprobanteDAO, Integer> implements ITIpoComprobanteService {
}
