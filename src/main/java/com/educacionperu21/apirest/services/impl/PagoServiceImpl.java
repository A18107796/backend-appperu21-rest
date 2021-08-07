package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.PagoDAO;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import com.educacionperu21.apirest.services.IPagoService;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceImpl extends GenericServiceWithStatusImpl<Pago, PagoDAO, Integer> implements IPagoService {

}
