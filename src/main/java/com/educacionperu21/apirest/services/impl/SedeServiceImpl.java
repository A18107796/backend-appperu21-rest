package com.educacionperu21.apirest.services.impl;

import com.educacionperu21.apirest.dao.SedeDAO;
import com.educacionperu21.apirest.entities.Sede;
import com.educacionperu21.apirest.generics.service.GenericServiceImpl;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatus;
import com.educacionperu21.apirest.generics.service.GenericServiceWithStatusImpl;
import com.educacionperu21.apirest.services.ISedeService;
import org.springframework.stereotype.Service;

@Service
public class SedeServiceImpl extends GenericServiceImpl<Sede, SedeDAO, Integer> implements ISedeService {
    
}
