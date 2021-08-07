package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.MonedaDAO;
import com.educacionperu21.apirest.entities.Moneda;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.IMonedaService;
import org.springframework.stereotype.Service;

@Service
public class MonedaServiceImpl extends GenericServiceImpl<Moneda, MonedaDAO, Integer> implements IMonedaService {
}
