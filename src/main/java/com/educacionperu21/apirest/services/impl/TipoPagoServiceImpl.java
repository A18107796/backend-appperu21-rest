package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.TipoPagoDAO;
import com.educacionperu21.apirest.entities.Tipo_Pago;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import com.educacionperu21.apirest.services.ITipoPagoService;
import org.springframework.stereotype.Service;

@Service
public class TipoPagoServiceImpl extends GenericServiceImpl<Tipo_Pago, TipoPagoDAO, Integer> implements ITipoPagoService {
}
