package com.educacionperu21.apirest.services;

import com.educacionperu21.apirest.dao.GenericJPAStatusRepository;
import com.educacionperu21.apirest.entities.Pago;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;

import java.util.Optional;

public interface IPagoService extends GenericServiceWithStatus<Pago, Integer> {

    Integer findLastId();
}
