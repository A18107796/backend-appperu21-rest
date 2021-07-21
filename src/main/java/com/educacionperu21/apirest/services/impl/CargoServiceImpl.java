package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.CargoDAO;
import com.educacionperu21.apirest.entities.Cargo;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.services.ICargoService;
import org.springframework.stereotype.Service;

@Service
public class CargoServiceImpl extends GenericServiceImpl<Cargo, CargoDAO, Integer> implements ICargoService {
}
